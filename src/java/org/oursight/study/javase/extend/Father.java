package org.oursight.study.javase.extend;

public class Father implements Cloneable {
	
	public void print() {
		next();
		System.out.println("This is in father");
	}
	
	public void next() {
		System.out.println("next in father");
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	

}
