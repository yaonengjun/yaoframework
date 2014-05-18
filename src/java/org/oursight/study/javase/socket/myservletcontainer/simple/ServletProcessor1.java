package org.oursight.study.javase.socket.myservletcontainer.simple;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.oursight.study.javase.socket.myservletcontainer.Constans;

public class ServletProcessor1 {

	public void process(MyServletRequest request, MyServletResponse response) {
		String uri = request.getUri();
		String serlvetName = uri.substring(uri.lastIndexOf("/") + 1);
		URLClassLoader loader = null;
		
		URL[] urls = new URL[1];
		URLStreamHandler urlStreamHandler = null;
		
		File classPath = new File(Constans.WEB_ROOT);
		try {
			// 生成repository 的方法与 org.apache.catalina.ClassLoaderFactory 中的
			// creatClassLoader 方法相同
			String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
			
			// 生成URL的方法与 org.apache.catalina.loader.StandardClassLoader
			// 中的addRepository 方法相同
			urls[0] = new URL(null, repository, urlStreamHandler);
			loader = new URLClassLoader(urls);
			
		} catch (MalformedURLException e) {
			System.out.println("URL form is error");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Class myClass = null;

		try {
			myClass = loader.loadClass(serlvetName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Servlet servlet = null;
		try {
			servlet = (Servlet) myClass.newInstance();
			servlet.service((ServletRequest) request, (ServletResponse) response);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
