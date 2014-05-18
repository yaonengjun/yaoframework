package org.oursight.study.javase.socket.myhttpserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

	private static final int BUFFER_SIZE = 1024;

	Request request;
	OutputStream outputStream;

	public Response(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fileInputStream = null;
		try {
			File file = new File(HttpServer.WEB_ROOT, request.getUri());
			if (file.exists()) {
				fileInputStream = new FileInputStream(file);
				int ch = fileInputStream.read();
				StringBuffer errMessages = new StringBuffer();
				errMessages.append("HTTP/1.1 200 OK\r\n");
				errMessages.append("Content-Type:text/html\r\n");
				errMessages.append("Content-Length:23\r\n");
				errMessages.append("\r\n");
				
				while (ch != -1) {
					outputStream.write(bytes, 0, ch);
					ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
				}
			} else {
				// if file not found
				StringBuffer errMessages = new StringBuffer();
				errMessages.append("HTTP/1.1 404 File Not Found\r\n");
				errMessages.append("Content-Type:text/html\r\n");
				errMessages.append("Content-Length:23\r\n");
				errMessages.append("\r\n");
				errMessages.append("<h1>File not found</h1>");
				outputStream.write(errMessages.toString().getBytes());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (fileInputStream != null)
				fileInputStream.close();
		}
	}

}
