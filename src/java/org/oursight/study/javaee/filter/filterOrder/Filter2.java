package org.oursight.study.javaee.filter.filterOrder;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Filter2 implements Filter {

	public void destroy() {
		System.out.println("Filter2.destroy()");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
		System.out.println("Filter2.doFilter() before fc.doFilter");
		fc.doFilter(req, resp);
		System.out.println("Filter2.doFilter() after fc.doFilter");
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Filter2.init()");
	}

}
