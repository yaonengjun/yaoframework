package org.oursight.study.javase.jdk15.staticimport;

import static java.lang.System.out;
import static java.util.Arrays.sort;
import static org.oursight.study.javase.jdk15.enumerated.EnumInClass.ComunicationType;
import static org.oursight.study.javase.jdk15.enumerated.EnumInClass.State.*;

import java.util.Arrays;

public class UseStaticImport {
	
	public static void main(String[] args) {
		// 引入的静态变量
		out.println("'out' is a static field imported");
		
		// 引入的静态方法
		int[] array = new int[]{2, 1,3};
		sort(array);
		out.println("toString: " + Arrays.toString(array));
		
		// 引入的enum：引入到具体值和引入到变量，调用方式是不一样的。
		System.out.println(STARTED);
		System.out.println(ComunicationType.HTTP);
	}
}
