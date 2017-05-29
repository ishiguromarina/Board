package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Message;
import beans.User;
import service.MessageService;

@WebServlet(urlPatterns = { "/newMessage" })
public class NewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	//doget
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/newmessage.jsp").forward(request, response);//jspと関連付ける
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {

			User user = (User) session.getAttribute("loginUser");

			Message message = new Message();
			message.setText(request.getParameter("message"));
			message.setTitle(request.getParameter("title"));
			message.setCategory(request.getParameter("category"));
			message.setUserId(user.getId());

			new MessageService().register(message);

			response.sendRedirect("./");
		} else {
			request.setAttribute("errorMessages", messages);

			request.getRequestDispatcher("/newmessage.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String message = request.getParameter("message");
		String title = request.getParameter("title");
		String category = request.getParameter("category");


		if (StringUtils.isEmpty(title) == true) {
			messages.add("タイトルを入力してください");
		}
		if (StringUtils.isEmpty(category) == true) {
			messages.add("カテゴリーを入力してください");
		}
		if (StringUtils.isEmpty(message) == true) {
			messages.add("メッセージを入力してください");
		}
		if (1000 < message.length()) {
			messages.add("1000文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}

