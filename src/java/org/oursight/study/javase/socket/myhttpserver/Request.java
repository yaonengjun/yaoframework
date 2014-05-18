package org.oursight.study.javase.socket.myhttpserver;

import java.io.IOException;
import java.io.InputStream;

public class Request {
	
	private InputStream inputStream;

	private String uri;

	public Request(InputStream input) {
		this.inputStream = input;
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
	
	private String parseUri(String requestString) {
		int index1 = requestString.indexOf(' ');
		int index2;
		if(index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);
			if (index2 > index1)
				return requestString.substring(index1 + 1, index2);
		}
		return null;
	}
	
	public String getUri() {
		return uri;
	}
}
