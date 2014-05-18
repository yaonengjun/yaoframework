package org.oursight.study.javaee.filter.encoding;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trs.idm.util.RequestUtil;

public class PostParametersInFilter implements Filter {

	
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
//		encodingAfterExtractPostParams(request, response);
		encodingBeforeExtractPostParams(request, response);
		
		fc.doFilter(req, resp);
	}
	
	private void encodingAfterExtractPostParams(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("====================================   PostParametersInFilter.encodingAfterExtractPostParams() start  ====================================");
		System.out.println("request servetPath is: " + request.getServletPath());
		System.out.println("request BEFORE get post parameters");
		System.out.println("request: " + request);
		System.out.println("request encoding: " + request.getCharacterEncoding());
		System.out.println();
		
		String postParams = new String(request.getParameter("userName").getBytes("ISO8859-1"));
		System.out.println("receive as ISO8859-1, parse as GBK: " + new String(postParams.getBytes(), "GBK"));
		System.out.println();
		
		System.out.println("request AFTER get post parameters");
		System.out.println("request: " + request);
		System.out.println("RequestUtil.getPostParametersAsString(request): " + RequestUtil.getPostParametersAsString(request));
		System.out.println("request  encoding: " + request.getCharacterEncoding());
		System.out.println();
		
		request.setCharacterEncoding("GBK");
		System.out.println("request AFTER set GBK encoding");
		System.out.println("request: " + request);
		System.out.println("RequestUtil.getPostParametersAsString(request): " + RequestUtil.getPostParametersAsString(request));
		System.out.println("request  encoding: " + request.getCharacterEncoding());
				
		System.out.println("====================================   PostParametersInFilter.encodingAfterExtractPostParams() finish  ====================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	private void encodingBeforeExtractPostParams(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("====================================   PostParametersInFilter.encodingAfterExtractPostParams() start  ====================================");
		System.out.println("request servetPath is: " + request.getServletPath());
		System.out.println("request BEFORE get post parameters");
		System.out.println("request: " + request);
		System.out.println("request encoding: " + request.getCharacterEncoding());
		System.out.println();
		
		request.setCharacterEncoding("GBK");
		System.out.println("request AFTER set GBK encoding");
		String postParams = new String(request.getParameter("userName").getBytes("ISO8859-1"));
		System.out.println("receive as ISO8859-1, parse as GBK: " + new String(postParams.getBytes(), "GBK"));
		System.out.println();
		
		System.out.println("request: " + request);
		System.out.println("RequestUtil.getPostParametersAsString(request): " + RequestUtil.getPostParametersAsString(request));
		System.out.println("request  encoding: " + request.getCharacterEncoding());
		
		RequestUtil.getPostParametersAsString(request);
		System.out.println("request AFTER get post parameters");
		System.out.println("request: " + request);
		System.out.println("RequestUtil.getPostParametersAsString(request): " + RequestUtil.getPostParametersAsString(request));
		System.out.println("request  encoding: " + request.getCharacterEncoding());
		System.out.println();
		
		
		System.out.println("====================================   PostParametersInFilter.encodingAfterExtractPostParams() finish  ====================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	


}
