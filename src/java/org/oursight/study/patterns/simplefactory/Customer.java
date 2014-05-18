package org.oursight.study.patterns.simplefactory;

public class Customer {
	
	public static void main(String[] args) {
		try {
			IFruit fruit1 = FruitGardener.factory("Apple");
			fruit1.plant();
			fruit1.grow();
			fruit1.harvest();
			System.out.println();
			
			IFruit fruit2 = FruitGardener.factory("Grape");
			fruit2.plant();
			fruit2.grow();
			fruit2.harvest();
			System.out.println();
			
			IFruit fruit3 = FruitGardener.factory("StrawBerry");
			fruit3.plant();
			fruit3.grow();
			fruit3.harvest();
			System.out.println();
			
			IFruit fruit4 = FruitGardener.factory("FruitOnlyExistInHaven");
			fruit4.plant();
			fruit4.grow();
			fruit4.harvest();
			System.out.println();
			
		} catch (BadFruitException e) {
			System.out.println("Sorry, we don't have that fruit you asked!");
			System.out.println(e);
			System.out.println();
		}
	}

}
