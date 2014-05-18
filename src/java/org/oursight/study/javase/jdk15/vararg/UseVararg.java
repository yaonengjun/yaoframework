package org.oursight.study.javase.jdk15.vararg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JDK1.5中的vararg用法。String... body可以是0个或者多个
 * 
 * @author yaonengjun,2011-3-27 下午03:42:54
 * 
 */
public class UseVararg {

	private List<String> bodyList;

	private String[] allBodies;

	/**
	 * 这种方式也是合法的
	 * 
	 * @param body
	 */

	// public UseVararg(String... body) { // 这种写法编译器不会报错，但是运行时会报错，因为两个构造函数实际上含义混乱了。
	// Exception in thread "main" java.lang.Error: Unresolved compilation problems:
	// The constructor UseVararg(String[]) is ambiguous
	// The constructor UseVararg(String[]) is ambiguous
	// public UseVararg(String... str, String last) { // 这种写法编译器会报错，vararg只能在最后一个。
	// public UseVararg(String... str, String... last) { // 这种编译器会报错，只能有一个vararg变量
	public UseVararg(String head, String... body) {
		allBodies = body;
		bodyList = Arrays.asList(body);

		// bodyList = new ArrayList<String>();
		// for (String bodyStr : body) {
		// bodyList.add(bodyStr);
		// }
	}

	public void printBodyList() {
		System.out.println(bodyList);
	}

	public void printBodyArray() {
		System.out.println(Arrays.toString(allBodies));
	}

	/**
	 * Vararg的一个常见用法， 取多个int值中最大的。
	 * 
	 * @param first
	 * @param rest
	 * @author yaonengjun,2011-3-27 下午03:45:54
	 */
	public int findMaxIntUsingVararg(int... values) {
		int max = 0;
		for (int i : values) {
			if (i > max)
				max = i;
		}
		return max;
	}

	public static void main(String[] args) {
		// 没有vararg
		UseVararg use1 = new UseVararg("head");
		use1.printBodyList();
		System.out.println();

		// 2个vararg
		UseVararg use2 = new UseVararg("head", "body1", "body2");
		use2.printBodyList();
		use2.printBodyArray();
		System.out.println();

		// 使用数组作为vararg ,编译器报错
		// UseVararg use3 = new UseVararg(new String[]{"1st", "2sd", "3rt" });

		// 巧用vararg，取到多个int中的最大值。
		System.out.println(use2.findMaxIntUsingVararg(1, 2, 3, 4, 2, 7, 9, 5));
		System.out.println();

		// 这种调用本身也是合法的，但是注意防止这种调用，因为可能会发生空指针错误。
		System.out.println(use2.findMaxIntUsingVararg());
		System.out.println(use2.findMaxIntUsingVararg(new int[] { 1, 2, 3 })); // 注意，对于Primitive，这种方式是合法的。
		System.out.println();
	}

}
