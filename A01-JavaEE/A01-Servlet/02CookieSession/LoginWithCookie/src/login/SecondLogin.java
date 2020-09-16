package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondLogin extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
         
         PrintWriter out = response.getWriter();
         Cookie ck[] = request.getCookies();
         
         out.print("Hello " + ck[0].getValue());
         out.close();

	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request,response);
	}
}
