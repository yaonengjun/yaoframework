package org.oursight.study.javase.socket.myservletcontainer.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

public class MyServletRequest implements ServletRequest {
	
	private InputStream inputStream;
	
	private String uri;
	
	// ------------ 自行定义的方法 ------------ 
	public String getUri() {
		return uri;
	}
	
	public MyServletRequest(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	/**
	 * 从Http请求头中，获取到 uri的方法，获取第一个和第二个空格之间的内容
	 * 
	 * @param requestString
	 * @return
	 */
	public String parseUri(String requestString) {
		int index1;
		int index2;
		index1 = requestString.indexOf(' ');
		if(index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);
			if (index2 > index1)
				return requestString.substring(index1 + 1, index2);
		}
		return null;
	}
	
	/**
	 * 读取Socket中获取到的字节
	 */
	public void parse() {
		StringBuffer request = new StringBuffer(2048);
		byte[] buffer = new byte[2048];
		int i;
		
		try {
			i = inputStream.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			i = -1;
		}
		
		for (int j = 0; j < i; j++) {
			request.append((char) buffer[j]);
		}
		uri = parseUri(request.toString());
	}
	
	
	// ------------ ServletRequest 接口中的方法 ------------ 
	
	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Enumeration getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Map getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Enumeration getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String[] getParameterValues(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRealPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}



}
