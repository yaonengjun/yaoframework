package org.oursight.study.javase;

public class Test {

	public static void main(String[] args) {
		int score = 9;
		switch (score) {
		case 0:
			System.out.println();
		default:
			System.out.println("Error");
		case 1:
			System.out.println("Good");
			break;
		case 2:
			System.out.println("Best");
		}
	}

}
