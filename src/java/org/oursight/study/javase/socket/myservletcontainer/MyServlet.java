package org.oursight.study.javase.socket.myservletcontainer;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.oursight.framework.yao.util.DateUtil;

public class MyServlet implements Servlet {

	
	public void destroy() {
		System.out.println("MyServlet.destroy()");
		System.out.println("MyServlet is destory...");
		System.out.println();
	}

	
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("MyServlet.init()");
		System.out.println("MyServlet is init...");
		System.out.println();
	}

	
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("MyServlet.service()");
		System.out.println("MyServlet 's service method is being excute. time: " + DateUtil.getCurrentDateTime());
		System.out.println();
	}

}
