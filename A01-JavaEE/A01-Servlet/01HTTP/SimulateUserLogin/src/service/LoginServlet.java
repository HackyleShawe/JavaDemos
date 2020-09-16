package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import domain.User;
import util.DataSourceUtils;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1、获得用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 2、从数据库中验证该用户名和密码是否正确
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from myuser where username=? and password=?";
		User user = null;
		try {
			user = runner.query(sql, new BeanHandler<User>(User.class), username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 3、根据返回的结果给用户不同显示信息
		if (user != null) {
			// 用户登录成功
			response.getWriter().write(user.toString() + ", you are login successfully.");
		} else {
			// 用户登录失败
			response.getWriter().write("sorry your username or password is wrong");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
