package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstLogin extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String name = request.getParameter("username");
		String username = new String(name.getBytes("ISO-8859-1"), "utf-8");

		out.print("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\"><body>");
		out.print("欢迎您, " + username);

		Cookie ck = new Cookie("uname", username);// creating cookie object
		response.addCookie(ck);// adding cookie in the response

		// creating submit button

		out.print("<form action='SecondLogin' method='post'>");
		out.print("<p>Cookies已在浏览器中设置，点击跳转到第二个Servlet中读取Cookies的值。</p>");
		out.print("<input type='submit' value='提交到第二个Servlet'>");
		out.print("</form>");

		out.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
