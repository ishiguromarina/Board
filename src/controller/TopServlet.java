package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Comment;
import beans.User;
import beans.UserMessage;
import service.CommentService;
import service.MessageService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		User user = (User) request.getSession().getAttribute("loginUser");//セッションからloginUserを取り出してる
		boolean isShowMessageForm;
		if (user != null) {
			isShowMessageForm = true;
		} else {
			isShowMessageForm = false;
		}

		List<UserMessage> messages = new MessageService().getMessage();

		request.setAttribute("messages", messages);
		request.setAttribute("isShowMessageForm", isShowMessageForm);//メッセージフォームを表示

		request.getRequestDispatcher("/top.jsp").forward(request, response);//jspと関連付ける
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		Comment comment = new Comment();
		comment.setText(request.getParameter("comment"));
		comment.setMessageId(Integer.parseInt(request.getParameter("messageId")));
		comment.setUserId(user.getId());

		new CommentService().register(comment);

		response.sendRedirect("./");






	}
}
