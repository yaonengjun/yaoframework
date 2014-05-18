package org.oursight.study.javase.socket.myservletcontainer.simple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import org.oursight.study.javase.socket.myservletcontainer.Constans;

public class MyServletResponse implements ServletResponse {

	private static final int BUFFER_SIZE = 1024;

	MyServletRequest request;
	OutputStream outputStream;
	PrintWriter printWriter;

	public MyServletResponse(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public void setRequest(MyServletRequest request) {
		this.request = request;
	}

	// ---------------- 自行定义的方法 -------------------

	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fileInputStream = null;

		try {
			File file = new File(Constans.WEB_ROOT, request.getUri());
			fileInputStream = new FileInputStream(file);
		
			int ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
			while(ch != -1) {
				outputStream.write(bytes, 0, BUFFER_SIZE);
				ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
			}
		} catch (FileNotFoundException e) {
			System.out.println("MyServletResponse.sendStaticResource() Error!");
			StringBuffer errMessage = new StringBuffer();
			errMessage.append("HTTP/1.1 404 File not found\r\n");
			errMessage.append("Content-Type: text/html\r\n");
			errMessage.append("Content-length: 23\r\n");
			errMessage.append("\r\n");
			errMessage.append("<h1>File not found!!!</h1>");
			outputStream.write(errMessage.toString().getBytes());
		}finally {
			if(fileInputStream != null)
				fileInputStream.close();
		}
		
		

	}


	// ---------------- 接口中定义的方法 -------------------

	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub

	}

	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public PrintWriter getWriter() throws IOException {
		printWriter = new PrintWriter(outputStream, true);
		return printWriter;
	}

	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub

	}

	public void resetBuffer() {
		// TODO Auto-generated method stub

	}

	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub

	}

	public void setContentLength(int arg0) {
		// TODO Auto-generated method stub

	}

	public void setContentType(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setLocale(Locale arg0) {
		// TODO Auto-generated method stub

	}


}
