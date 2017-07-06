package com.itheima.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class QuickStratServlet implements Servlet {
	/* init方法
	 * servlet对象创建的时候执行
	 * 什么时候创建？默认第一次访问servlet时，创建该对象。
	 * ServletConfig：代表该servlet对象的配置信息。
	 * */
	public void init(ServletConfig arg0) throws ServletException {
		//1.获得该servlet的name --- <servlet-name>abc</servlet-name>
		String servletName = arg0.getServletName();
		System.out.println(servletName);
		//2.获得该servlet的一些初始化参数
		String initParameter = arg0.getInitParameter("url");
		System.out.println(initParameter);
		//3.获得Servletcontext对象
		ServletContext servletContext = arg0.getServletContext();
		
		System.out.println("init running...");
	}
	/* service
	 * 每次请求都会执行
	 * 
	 * ServletRequest：代表请求，认为ServletRequest内部封装的是http请求的信息
	 * ServletResponse：代表响应，认为要封装的响应的信息。
	 * */
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("QuickStratServlet runing...");
		arg1.getWriter().write("QuickStratServlet runing...");
	}
	/* destroy
	 * servlet销毁的时候执行
	 * 什么时候销毁？服务器关闭，servlet就销毁了
	 * */
	public void destroy() {
		System.out.println("destroy running...");
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	


	
}
