package org.oursight.study.javase.object;

public class EqualsLearing {
	
	public static void main(String[] args) {
		EqualsLearing learning = new EqualsLearing();
		learning.isObjectEqualsString();
	}
	
	public void isObjectEqualsString() {
		String str = "string";
		Object obj = "strin" + "g";
		System.out.println("obj.equals(str): " + obj.equals(str));
		System.out.println();
		
		System.out.println("str.equals(obj): " + str.equals(obj));
		System.out.println();
		
	}

}
