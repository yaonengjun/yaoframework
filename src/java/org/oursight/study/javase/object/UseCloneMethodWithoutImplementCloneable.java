package org.oursight.study.javase.object;

public class UseCloneMethodWithoutImplementCloneable {
	
	/**
	 * 必须实现Cloneable接口，否则会抛出CloneNotSupportedException异常。  
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		System.out.println("This is rewrited method of CloneMethod class");
		return super.clone();
	}
	
	public static void main(String[] args) {
		UseCloneMethodWithoutImplementCloneable method = new UseCloneMethodWithoutImplementCloneable();
		try {
			method.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
