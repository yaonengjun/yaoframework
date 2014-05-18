package org.oursight.study.javase.jdk15.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 本类展示了使用Generic的一些例子
 * 
 * @author yaonengjun,2011-3-27 上午10:02:05
 * 
 */
public class UseGenericInCollection {

	/**
	 * 在List中使用Generic
	 * 
	 * @author yaonengjun,2011-3-27 上午10:01:29
	 */
	public void inList() {
		List<String> list = new ArrayList<String>();
		// 此时只能添加String
		list.add("i am string");
	}

	public void inDifferentList() {
		List<Integer> list1 = new ArrayList<Integer>();
		// List<Number> list2 = list1; // 这种情况编译器会报错：虽然Integer是Number的子类，但是这样转型也是不允许的。
	}

	/**
	 * 在Map中使用Generic
	 * 
	 * @author yaonengjun,2011-3-27 上午10:01:40
	 */
	public void inMap() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("str1", 1);
		// map.put("str1", String); // 此时编译器会报错
	}

	/**
	 * 在遍历中使用Generic
	 * 
	 * @author yaonengjun,2011-3-27 上午10:01:53
	 */
	public void inIterator() {
		List<String> list = new ArrayList<String>();
		list.add("str1");
		list.add("str2");
		list.add("str3");

		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);

		// 声明 Iterator<Integer>后，循环中将不必再强制转型。
		for (Iterator<Integer> it = list1.iterator(); it.hasNext();) {
			Integer integer = it.next();

		}
	}
	
	/**
	 * 将泛型声明为?时，你可以使用任意的类型。此时编译器不会有unchecked的警告。
	 * @author yaonengjun,2011-3-27 上午10:42:49
	 */
	public void youCanUseAnyType(List<?> listOfAnyType) {
		
		for (Iterator<?> it = listOfAnyType.iterator(); it.hasNext();) {
			Object object = (Object) it.next();
			System.out.println(object);
			
		}
	}

}
