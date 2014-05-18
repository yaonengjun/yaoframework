package org.oursight.study.javase.charset;

import java.io.UnsupportedEncodingException;

public class StringCharsetLearner {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String cn = "中文";
		byte[] bytesOfCnDefault = cn.getBytes();
		for (int i = 0; i < bytesOfCnDefault.length; i++) {
			byte b = bytesOfCnDefault[i];
			System.out.println("bytesOfCnDefault[" + i + "]: " + b);
		}
		System.out.println();
		
		byte[] bytesOfCnDefaultUTF8 = cn.getBytes("UTF-8");
		for (int i = 0; i < bytesOfCnDefaultUTF8.length; i++) {
			byte b = bytesOfCnDefaultUTF8[i];
			System.out.println("bytesOfCnDefaultUTF8[" + i + "]: " + b);
		}
		System.out.println();
		
		byte[] bytesOfCnDefaultGBK = cn.getBytes("GBK");
		for (int i = 0; i < bytesOfCnDefaultGBK.length; i++) {
			byte b = bytesOfCnDefaultGBK[i];
			System.out.println("bytesOfCnDefaultGBK[" + i + "]: " + b);
		}
		System.out.println();
	}

}
