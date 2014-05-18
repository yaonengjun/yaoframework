package org.oursight.study.patterns.simplefactory;

public class StrawBerry implements IFruit{
	
	public void plant() {
		System.out.println("StrawBerry has been planted");
		
	}
	
	public void grow() {
		System.out.println("StrawBerry is growing");
	}

	
	public void harvest() {
		System.out.println("StrawBerry has been harvested");
		
	}
}
