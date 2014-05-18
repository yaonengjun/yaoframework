package org.oursight.study.javaee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(new java.sql.Time(System.currentTimeMillis()));
		System.out.println();
		
		resp.getWriter().print("OK!!!!!!!!");
//		super.doGet(req, resp);
	}

}
