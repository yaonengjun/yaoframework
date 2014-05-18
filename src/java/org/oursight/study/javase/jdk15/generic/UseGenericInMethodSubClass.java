package org.oursight.study.javase.jdk15.generic;


public class UseGenericInMethodSubClass<T> extends UseGenericInClass<T> {
	
	public static void main(String[] args) {
		UseGenericInMethodSubClass<String> theExample = new UseGenericInMethodSubClass<String>();
//		theExample.add(1); // 非法
		theExample.add("TTTT");
		System.out.println(theExample.getValue());
//		theExample.
	}

	

}
