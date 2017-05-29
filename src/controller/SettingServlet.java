package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Branch;
import beans.Department;
import beans.User;
import service.BranchService;
import service.DepartmentService;
import service.UserService;

@WebServlet(urlPatterns = { "/setting" })
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//doget
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("id"));
		User user = new UserService().getUser(userId);

		List<Branch> branch = new BranchService().getBranch();
		List<Department> department = new DepartmentService().getDepartment();

		request.setAttribute("user", user);
		request.setAttribute("branch", branch);
		request.setAttribute("department", department);
		request.getRequestDispatcher("settings.jsp").forward(request, response);
	}


//	@Override
//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws IOException, ServletException {
//
//		List<String> messages = new ArrayList<String>();
//		HttpSession session = request.getSession();
//
//		User editUser = getEditUser(request);
//		session.setAttribute("editUser", editUser);
//
//		if (isValid(request, messages) == true) {
//
//			try {
//				new UserService().update(editUser);
//			}
//			catch (NoRowsUpdatedRuntimeException e) {
//				session.removeAttribute("editUser");
//				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
//				session.setAttribute("errorMessages", messages);
//				response.sendRedirect("setting");
//			}
//
//			session.setAttribute("loginUser", editUser);
//			session.removeAttribute("editUser");
//
//			response.sendRedirect("./");
//		} else {
//			session.setAttribute("errorMessages", messages);
//			response.sendRedirect("setting");
//		}
//	}
//
//	private User getEditUser(HttpServletRequest request)
//		throws IOException, ServletException {
//
//		HttpSession session = request.getSession();
//		User editUser = (User) session.getAttribute("editUser");
//
//		editUser.setName(request.getParameter("name"));
//		editUser.setAccount(request.getParameter("account"));
//		editUser.setPassword(request.getParameter("password"));
////		editUser.setBranchId(request.getParameter("branchId"));
////		editUser.setDepartmentId(request.getParameter("departmentId"));
//		return editUser;
//	}
//
//
//	private boolean isValid(HttpServletRequest request, List<String> messages) {
//
//		String account = request.getParameter("account");
//		String password = request.getParameter("password");
//
//		if (StringUtils.isEmpty(account) == true) {
//			messages.add("アカウント名を入力してください");
//		}
//		if (StringUtils.isEmpty(password) == true) {
//			messages.add("パスワードを入力してください");
//		}
//		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
//		if (messages.size() == 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
}
