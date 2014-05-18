package org.oursight.study.javase.interfaceLearning.java.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class ComparableInterface {
	
	public static void main(String[] args) {
		String [] strArray = {"bb","ab", "ac", "bc", "c"};
		
		Set s = new TreeSet();
		s.addAll(Arrays.asList(strArray));
		System.out.println(s);
	}

}
