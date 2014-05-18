//package org.oursight.study.javase.socket.myservletcontainer.simple;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.InetAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.UnknownHostException;
//
//import org.oursight.study.javase.socket.myhttpserver.Request;
//import org.oursight.study.javase.socket.myhttpserver.Response;
//
//public class HttpServletServer {
//	
//	/**
//	 * 关闭HTTP服务器的命令
//	 */
//	private static final String CMD_SHUTDOWN = "/SHUTDOWN";
//	
//	private boolean shutdown = false;
//	
//	public void waitForConnection() throws IOException {
//		ServerSocket serverSocket = null;
//		int port = 8080;
//
//		try {
//			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//			System.exit(1);
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.exit(1);
//		}
//		System.out.println("server starts successfully, now is in service...");
//		
//		while (!shutdown) {
//			try {
//				InputStream inputStream = null;
//				OutputStream outputStream = null;
//
//				Socket socket = serverSocket.accept();
//				inputStream = socket.getInputStream();
//				outputStream = socket.getOutputStream();
//				MyServletRequest request = new MyServletRequest(inputStream);
//				request.parse();
//				
//				MyServletResponse response = new MyServletResponse(outputStream);
//				response.setRequest(request);
//				
//				
//				if(request.getUri().startsWith("/servlet/")) {
//					// 如果请求的是servlet资源
//					ServletProcessor servletProcessor = new ServletProcessor();
//					servletProcessor.process(request, response);
//				}else {
//					// 如果请求的是静态资源
//					StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
//					staticResourceProcessor.process(request, response);
//				}
//				
//				
//				
//				response.sendStaticResource();
//				
//				socket.close();
//				shutdown = request.getUri().equals(CMD_SHUTDOWN);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				continue;
//			}
//		}
//		System.out.println("server is stopping...");
//		serverSocket.close();
//	}
//	
//	public static void main(String[] args) throws IOException {
//		HttpServletServer server = new HttpServletServer();
//		server.waitForConnection();
//	}
//
//
//}
