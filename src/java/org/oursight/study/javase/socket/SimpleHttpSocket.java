package org.oursight.study.javase.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleHttpSocket {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket socket = new Socket("127.0.0.1", 7070);
		System.out.println("socket: " + socket);
		
		OutputStream outputStream = socket.getOutputStream();
		boolean autoflush = true;
		PrintWriter printWriter = new PrintWriter(outputStream, autoflush);
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		// send a http request to the web server
		printWriter.println("GET /index.jsp HTTP/1.1");
		printWriter.println("Host: 127.0.0.1:7070");
		printWriter.println("Connection: Close");
		printWriter.println();

		// read the response from server
		boolean loop = true;
		StringBuffer stringBuffer = new StringBuffer();
		while (loop) {
			if (bufferReader.ready()) {
				int i = 0;
				while (i != -1) {
					i = bufferReader.read();
					stringBuffer.append((char) i);
				}
				loop = false;
			}
			// Thread.currentThread().sleep(50);
		}

		System.out.println(stringBuffer.toString());
		socket.close();

	}
}
