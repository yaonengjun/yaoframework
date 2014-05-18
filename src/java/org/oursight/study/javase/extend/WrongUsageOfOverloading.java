package org.oursight.study.javase.extend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 对于重载的错误使用示例。<br>
 * <b>注意要避免让一个类中有相同参数数目的重载方法。</b>
 * 
 * @author yaonengjun,2010-12-7 下午09:58:00
 *
 */
public class WrongUsageOfOverloading {
	
	public static void methodA(List list) {
		System.out.println("This is a list");
	}
	
	public static void methodA(Map map) {
		System.out.println("This is a map");
	}
	
	/**
	 * 实际上所有代码执行的都是这个方法
	 * 
	 * @param c
	 * @author yaonengjun,2010-12-7 下午09:57:36
	 */
	public static void methodA(Collection c) {
		System.out.println("This is a unknow type collection");
	}
	
	

	public static void main(String[] args) {
		Collection[] tests = new Collection[] { new HashSet(), new ArrayList(), new HashMap().values() } ;
		for (int i = 0; i < tests.length; i++) {
			methodA(tests[i]);
			
		}
	}

}
