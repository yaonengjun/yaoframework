package org.oursight.study.patterns.simplefactory;

public class Apple implements IFruit{
	
	private int treeAge;

	public void plant() {
		System.out.println("Apple has been planted");
		
	}
	
	public void grow() {
		System.out.println("Apple is growing");
	}

	
	public void harvest() {
		System.out.println("Apple has been harvested");
		
	}

	public int getTreeAge() {
		return treeAge;
	}

	public void setTreeAge(int treeAge) {
		this.treeAge = treeAge;
	}
}
