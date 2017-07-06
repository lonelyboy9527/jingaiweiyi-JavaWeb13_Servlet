package com.itheima.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.domain.User;
import com.itheima.utils.DataSourceUtils;

public class LoginServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		//在servletcontext对象中，存一个数据count
		int count = 0;
		ServletContext servletContext = this.getServletContext();
		servletContext.setAttribute("count", count);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//1.获得用户名字和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//2.从数据库验证该用户名和密码是否正确
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM user WHERE username=? AND password=?";
		Object[] params = {username, password};
		System.out.println("username--->" + username);
		System.out.println("password--->" + password);
		User user = null;
		try {
			user = runner.query(sql, new BeanHandler<User>(User.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//3.根据返回的结果给用户不同的显示
		if (user!=null) {
			// 从servletcontext中取出count进行++运算
			ServletContext servletContext = this.getServletContext();
			Integer count = (Integer)servletContext.getAttribute("count");
			count ++ ;
			
			//用户登录成功
			System.out.println("get username--->" + user.getUsername());
			System.out.println("get password--->" + user.getPassword());
			response.getWriter().write(user.toString() + "you are sucess login person:" + count);
			servletContext.setAttribute("count", count);
			
		} else {
			//用户登录失败
			response.getWriter().write("sorry login failed!");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
