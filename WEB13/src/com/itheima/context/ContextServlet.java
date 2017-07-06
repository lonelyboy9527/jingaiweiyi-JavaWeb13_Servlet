package com.itheima.context;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ContextServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得servletContext对象
		ServletContext servletContext = this.getServletContext();
		//1.获得初始化参数
		String initParameter = servletContext.getInitParameter("driver");
		System.out.println(initParameter);
		
		
		//2.分别获得a b c d.txt的绝对路径
		// getRealPath 相对于该web应用的相对地址
		//2.1获得a.txt
		String realPath_A = servletContext.getRealPath("/a.txt");
		System.out.println(realPath_A);
		//2.2获得b.txt
		String realPath_B = servletContext.getRealPath("/WEB-INF/b.txt");
		System.out.println(realPath_B);
		//2.3获得c.txt
		String realPath_C = servletContext.getRealPath("/WEB-INF/classes/c.txt");
		System.out.println(realPath_C);
		//2.4获得d.txt(获取不到)
//		String realPath_D = servletContext.getRealPath("");
//		System.out.println(realPath_D);
		
		//在读取 src(classes)下的资源时可以使用类加载器---专门加载classes下文件的
		//getResource 参数也是一个相对地址，相对于 classes
		String path = ContextServlet.class.getClassLoader().getResource("c.txt").getPath();
		System.out.println(path);
		
		
		//3.域对象---向servletContext中存数据
		servletContext.setAttribute("name", "zhangsan");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
