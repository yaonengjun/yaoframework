package org.oursight.study.javase.jdk15.util.arrays;

import java.util.Arrays;

/**
 * 对java.util.Arrays类的一些使用示例
 * @author yaonengjun,2011-3-26 下午09:15:44
 *
 */
public class UseArrays {

	private int[] array;

	public UseArrays(int numbers) {
		array = new int[numbers];

		for (int i = 0; i < array.length; i++) {
			array[i] = (1000 - (300 + i));
		}
	}

	public int[] getArray() {
		return array;
	}

	public static void main(String[] args) {
		UseArrays useArrays = new UseArrays(30);
		int[] myArray = useArrays.getArray();
		int[] myArrayCopy = useArrays.getArray().clone();

		// 比较两个Array
		if (Arrays.equals(myArray, myArrayCopy)) {
			System.out.println("The two array is equal");
		} else {
			System.out.println("The two array is NOT equal");
		}
		System.out.println();

		// 为Array插入值
		Arrays.fill(myArray, 2, 10, 999);
		Arrays.fill(myArrayCopy, 2, 2, 999); // 这个插入实际上没有起作用。
		myArray[29] = 1000;

		// 打印Array中的值
		System.out.println("myArray(Origin):" + myArray);
		System.out.println("myArray        :" + Arrays.toString(myArray));
		System.out.println("myArrayCopy    :" + Arrays.toString(myArrayCopy));
		System.out.println();

		// 将其排序：
		Arrays.sort(myArray); // 没有使用toString方法时
		System.out.println("myArray after sort: " + Arrays.toString(myArray));
		System.out.println();

		// 获得数组中执行值所在的位置。
		int existValueIndex = Arrays.binarySearch(myArray, 673);
		int noExistValueIndex1 = Arrays.binarySearch(myArray, 99999); // 如果找到的是不存在的值
		int noExistValueIndex2 = Arrays.binarySearch(myArray, 999999); // 如果找到的是不存在的值
		int multiValueIndex = Arrays.binarySearch(myArray, 999); // 如果找的值有多个
		System.out.println("foundIndex:" + existValueIndex);
		System.out.println("noExistValueIndex1:" + noExistValueIndex1);
		System.out.println("noExistValueIndex2:" + noExistValueIndex2);
		System.out.println("multiValueIndex:" + multiValueIndex);
		System.out.println();

		// 打印多维数组
		int[][] mutiArray = { { 3, 2, 1 }, { 6, 4, 5 }, { 8, 9, 7 } };
		System.out.println("mutiArray(Origin): " + mutiArray);
		System.out.println("mutiArray(toString): " + Arrays.toString(mutiArray));
		System.out.println("mutiArray(deepToString): " + Arrays.deepToString(mutiArray));
		System.out.println();
		
		// 比较多维数组
		int[][] mutiArray1 = { { 0, 0, 0 }, { 6, 4, 5 }, { 8, 9, 7 } };
		int[][] mutiArray2 = { { 3, 2, 1 }, { 6, 4, 5 }, { 8, 9, 7 } };
		System.out.println(Arrays.deepEquals(mutiArray, mutiArray1));
		System.out.println(Arrays.deepEquals(mutiArray, mutiArray2));
		System.out.println();
	}

}
