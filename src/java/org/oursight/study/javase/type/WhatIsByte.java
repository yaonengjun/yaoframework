package org.oursight.study.javase.type;

public class WhatIsByte {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		turnStringIntoByte();
		
	}
	
	public static void turnStringIntoByte() {
		String engStr = "hello world";
		byte [] engStrBytes = engStr.getBytes();
		System.out.println("eng str as byte: " + engStrBytes);
		for (int i = 0; i < engStrBytes.length; i++) {
			System.out.println("engStrBytes[" + i +"]: " + engStrBytes[i]);
		}
		
	}

}
