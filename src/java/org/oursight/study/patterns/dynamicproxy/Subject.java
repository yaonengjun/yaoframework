package org.oursight.study.patterns.dynamicproxy;

public class Subject implements ISubject {

	private String name;
	
	@Override
	public void request() {

		System.out.println("**** I am the real subject ****");
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		System.out.println("**** After set name in Subject, this.name:" + this.name + " ****");
	}
	

}
