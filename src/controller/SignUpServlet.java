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

import beans.Branch;
import beans.Department;
import beans.User;
import service.BranchService;
import service.DepartmentService;
import service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Branch> branch = new BranchService().getBranch();
		request.setAttribute("branch", branch);

		List<Department> department = new DepartmentService().getDepartment();
		request.setAttribute("department", department);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

			List<String> messages = new ArrayList<String>();
			HttpSession session = request.getSession();

			//入力された情報をuserというひとつの変数にまとめる
			User user = new User();

			//jspから取ってきたstring型の情報を変数にint型として格納
			int branch_id = Integer.parseInt(request.getParameter("branchId"));
			int department_id = Integer.parseInt(request.getParameter("departmentId"));

			user.setName(request.getParameter("name"));
			user.setAccount(request.getParameter("account"));
			user.setPassword(request.getParameter("password"));
			user.setBranchId(branch_id);
			user.setDepartmentId(department_id);

			if (isValid(request, messages) == true) {//isvalidは入力チェック

				new UserService().register(user);//okならuserの情報をserviceに渡す
				response.sendRedirect("./");//帰ってきた情報をリダイレクト
		} else {

			request.setAttribute("user", user );//ngならユーザー情報をリクエストにセット
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			//response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}
//		if(account.matches("^\\da-zA-Z{6,20}$")){
//			messages.add("アカウント名は半角英数字6文字以上20字以下で入力してください");
//		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
//		if(account.matches("^\\da-zA-Z{6,20}$")){
//			messages.add("パスワードは記号を含む半角英数字6文字以上で入力してください");
//		}
		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}