package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

@WebFilter({"/top.jsp", "/signup.jsp", "/newmessage.jsp","/manageuser.jsp","/setting.jsp"})
public class LoginFilter implements Filter{
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	  throws IOException, ServletException{


		  HttpSession session = ((HttpServletRequest)request).getSession();
		  session.getAttribute("loginUser"); //セッションの中から、"loginUser"という文字列を取り出す
		  User user = (User)session.getAttribute("loginUser"); //"loginUser"をUser型の変数に置き換える

		  //user情報がnullだったらリダイレクトしてリターン。
		  //nullじゃなければサーブレットを実行
		if (user == null){
			((HttpServletResponse)response).sendRedirect("login");
			return;
		}
		chain.doFilter(request, response); // サーブレットを実行
	}


	//Filterに絶対必要なメソッド↓
	@Override
	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ
	}

}