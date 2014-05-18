package org.oursight.study.javase.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketTest {

	/**
	 * @param args
	 * @author yaonengjun,Jan 8, 2012 7:40:05 PM
	 */
	public static void main(String[] args) {
		String idsIP = "210.75.211.115";
		int port = 2005;
		Socket socket = null;
		try {
			socket =  new Socket(idsIP, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(socket == null) {
			System.out.println("can not create socket on "+idsIP + ":" +port);
			return ;
		}
		
		OutputStream out = null;
		try {
			out = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(out == null) {
			System.out.println("can not read from  socket ");
			return ;
		}
		
		
		String str = "495050490100014a000000510000000054525357434d5636410a0d2f6c6f676f75742e6a73700a0d6e0a0d49424d20576562537068657265204170706c69636174696f6e205365727665722f352e312028536572766c657420322e33290a0d5b4a617661456e765d3a312e342e312c49424d20436f72706f726174696f6e3b5b4d656d6f72795d3a323034384d423b5b757365725d3a726f6f743b5b757365726469725d3a2f6f70742f5765625370686572652f4170705365727665723b5b4f535d3a4c696e75782c322e362e392d37382e454c736d702c7838360a0d56322e312c20323030362d31312d32312031333a31360a0d66696c653a2f6f70742f5765625370686572652f4170705365727665722f696e7374616c6c6564417070732f4265696a696e673330302f77636d2e6561722f77636d2e7761722f5745422d494e462f6c69622f7472736964732d6167656e742e6a61720a0d";
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("Try socket: " + i + " times" );
				out.write(str.getBytes());
				out.write(25);
				out.flush();
				
				
				InputStream in = socket.getInputStream();
				System.out.println("The response of IDSServer is: " + in);
				System.out.println();
				System.out.println();

			}
		} catch (Exception e) {
			System.out.println("Sending request to IDSServer error");
			e.printStackTrace();
		}
		
		
	}

}
