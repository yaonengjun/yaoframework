package org.oursight.study.javase.type;

public class IntegerUsage {
	
	public static void main(String[] args) {
		String max = new Integer(Integer.MAX_VALUE).toString();
		String moreThanMax = "100000000000";
		
		System.out.println(max);
		System.out.println((Integer.MAX_VALUE)/10000);
		System.out.println("Smaller than max: " + new Integer(max).intValue());
		System.out.println("Bigger than max: " + new Integer(moreThanMax).intValue());
	}

}
