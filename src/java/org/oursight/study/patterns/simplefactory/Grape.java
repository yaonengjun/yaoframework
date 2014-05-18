package org.oursight.study.patterns.simplefactory;

public class Grape implements IFruit{
	
	private boolean seedless;
	

	public void plant() {
		System.out.println("Grape has been planted");
		
	}
	
	public void grow() {
		System.out.println("Grape is growing");
	}

	
	public void harvest() {
		System.out.println("Grape has been harvested");
		
	}

	public boolean isSeedless() {
		return seedless;
	}

	public void setSeedless(boolean seedless) {
		this.seedless = seedless;
	}

}
