package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

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

		User user = new UserService().getSettingUser(userId);
		List<Branch> branch = new BranchService().getBranch();
		List<Department> department = new DepartmentService().getDepartment();

		request.setAttribute("user", user);
		request.setAttribute("branch", branch);
		request.setAttribute("department", department);
		request.getRequestDispatcher("setting.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		User editUser = getEditUser(request);
		request.setAttribute("editUser", editUser);

		if (isValid(request, messages) == true) {
			new UserService().update(editUser);

			response.sendRedirect("./");
		} else {
			request.setAttribute("errorMessages", messages);

			List<Branch> branch = new BranchService().getBranch();
			List<Department> department = new DepartmentService().getDepartment();

			request.setAttribute("user", editUser);
			request.setAttribute("branch", branch);
			request.setAttribute("department", department);
			request.getRequestDispatcher("setting.jsp").forward(request, response);
		}
	}

	private User getEditUser(HttpServletRequest request)
		throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));
		User editUser = new UserService().getSettingUser(id);

		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));

		editUser.setName(request.getParameter("name"));
		editUser.setAccount(request.getParameter("account"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setBranchId(branchId);
		editUser.setDepartmentId(departmentId);
		return editUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String account = request.getParameter("account");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
