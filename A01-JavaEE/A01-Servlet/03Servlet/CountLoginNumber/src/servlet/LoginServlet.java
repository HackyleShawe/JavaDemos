package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取数据
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		//向客户端输出内容
		PrintWriter pw = response.getWriter();
		
		//2.校验数据
		if("admin".contentEquals(userName) && "123".contentEquals(password)) {
			//获取以前存的值 ， 然后在旧的值基础上  + 1 
			Object obj = getServletContext().getAttribute("count") ; 
			
			//默认就是0次
			int totalCount = 0 ;
			if(obj != null){
				totalCount = (int) obj;
			}
			
			System.out.println("已知登录成功的次数是："+totalCount);
			
			//给这个count赋新的值
			getServletContext().setAttribute("count", totalCount+1);
			
			
			//2. 跳转到成功的界面 
			//设置状态码? 重新定位 状态码
			response.setStatus(302);
			//定位跳转的位置是哪一个页面。
			response.setHeader("Location", "login_success.html");
		} else{
			pw.write("login failed..");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
