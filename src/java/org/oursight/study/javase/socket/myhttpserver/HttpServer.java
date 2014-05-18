package org.oursight.study.javase.socket.myhttpserver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 一个简单的HttpServer
 * 
 * @author yaonengjun
 * 
 */
public class HttpServer {

	/**
	 * 存放页面的目录的物理路径
	 */
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "WebRoot";

	/**
	 * 关闭HTTP服务器的命令
	 */
	private static final String CMD_SHUTDOWN = "/SHUTDOWN";

	private boolean shutdown = false;

	public void waitForConnection() throws IOException {
		ServerSocket serverSocket = null;
		int port = 8080;

		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("server starts successfully, now is in service...");
		while (!shutdown) {
			try {
				InputStream inputStream = null;
				OutputStream outputStream = null;

				Socket socket = serverSocket.accept();
				inputStream = socket.getInputStream();
				outputStream = socket.getOutputStream();
				Request request = new Request(inputStream);
				request.parse();
				
				Response response = new Response(outputStream);
				response.setRequest(request);
				response.sendStaticResource();
				
				socket.close();
				shutdown = request.getUri().equals(CMD_SHUTDOWN);
				
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		System.out.println("server is stopping...");
		serverSocket.close();
	}

	public static void main(String[] args) throws IOException {
		HttpServer myServer = new HttpServer();
		myServer.waitForConnection();
	}
}
