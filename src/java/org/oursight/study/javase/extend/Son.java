package org.oursight.study.javase.extend;

public class Son extends Father implements Cloneable {
	public void print() {
		super.print();
		
		System.out.println("This is in Son");
		next();
	}
	
	public void next() {
		System.out.println("next in Son");
	}
	
	public static void main(String[] args) {
		Son son = new Son();
		son.print();
	}
}
