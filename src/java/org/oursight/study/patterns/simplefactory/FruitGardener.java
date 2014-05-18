package org.oursight.study.patterns.simplefactory;

public class FruitGardener {
	
	public static IFruit factory(String which) throws BadFruitException {
		if("Apple".equals(which)) {
			return new Apple();
		}else if("Grape".equals(which)) {
			return new Grape();
		}else if("StrawBerry".equals(which)) {
			return new StrawBerry();
		}else {
			throw new BadFruitException("No such fruit!");
		}
	}
	
}
