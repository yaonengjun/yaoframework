package org.oursight.study.javase.collections;

import java.util.Arrays;

public class ArraysStudy {

	/**
	 * 判断一个数字是否在数组当中
	 * 
	 * @author yaonengjun,Apr 21, 2012 6:01:46 PM
	 */
	public static void judgeWhetherInArray() {
		Integer[] ints = new Integer[] { 1, 2, 3 };
		System.out.println(Arrays.binarySearch(ints, 1));
		System.out.println(Arrays.binarySearch(ints, 3));
		System.out.println(Arrays.binarySearch(ints, 4));

	}

	public static void main(String[] args) {
		judgeWhetherInArray();

	}

}
