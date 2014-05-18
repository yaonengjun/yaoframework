package org.oursight.study.javase.socket.myservletcontainer.simple;

import java.io.IOException;

public class StaticResourceProcessor {

	public void process(MyServletRequest request, MyServletResponse response) {
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
