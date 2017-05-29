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

@WebServlet(urlPatterns = { "/manageUser" })
public class ManageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//doget
	@Override
	protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws IOException, ServletException {


		List<User> user = new UserService().getUser();
		List<Branch> branch = new BranchService().getBranch();
		List<Department> department = new DepartmentService().getDepartment();

		request.setAttribute("user", user);
		request.setAttribute("branch", branch);
		request.setAttribute("department", department);

		request.getRequestDispatcher("manageuser.jsp").forward(request, response);//jspと関連付ける
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}
