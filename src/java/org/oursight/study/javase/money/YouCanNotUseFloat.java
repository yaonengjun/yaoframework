package org.oursight.study.javase.money;

import java.text.NumberFormat;

public class YouCanNotUseFloat {

	public static void main(String[] args) {
		float a = 8250325.12f;
		float b = 4321456.31f;
		float c = a + b;
		System.out.println(NumberFormat.getCurrencyInstance().format(c));
	}

}
