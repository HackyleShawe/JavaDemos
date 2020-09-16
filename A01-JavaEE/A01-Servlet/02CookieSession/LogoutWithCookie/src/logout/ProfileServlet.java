package logout;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		request.getRequestDispatcher("index.html").include(request, response);
		Cookie ck[] = request.getCookies();
		if (ck != null) {
			String name = ck[0].getValue();
			if (!name.equals("") || name != null) {
				out.print("欢迎您来到个人信息中心. ");
				out.print("您好, " + name);
			}
		} else {
			out.print("请先登录!");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		out.close();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request,response);
	}
}
