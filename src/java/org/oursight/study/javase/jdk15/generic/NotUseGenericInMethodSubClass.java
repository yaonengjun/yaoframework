package org.oursight.study.javase.jdk15.generic;


public class NotUseGenericInMethodSubClass extends UseGenericInClass {
	
	public static void main(String[] args) {
		NotUseGenericInMethodSubClass theExample = new NotUseGenericInMethodSubClass();
		theExample.add("TTTT");
		System.out.println(theExample.getValue());
//		theExample.
	}

	

}
