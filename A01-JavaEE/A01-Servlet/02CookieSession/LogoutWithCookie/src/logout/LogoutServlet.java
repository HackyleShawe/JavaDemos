package logout;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8"); 
        request.setCharacterEncoding("UTF-8");  
        
        PrintWriter out = response.getWriter();
        
		request.getRequestDispatcher("index.html").include(request, response);
        Cookie ck = new Cookie("name", "");
        ck.setMaxAge(0);
        response.addCookie(ck);

        out.print("您已成功注销!");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request,response);
	}
}
