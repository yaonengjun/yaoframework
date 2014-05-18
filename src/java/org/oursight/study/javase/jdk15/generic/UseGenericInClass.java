package org.oursight.study.javase.jdk15.generic;

import java.util.ArrayList;
import java.util.List;

public class UseGenericInClass<T> {

	// 注意：静态变量中不能声明Generic
	// private static List<T> youCanotUseGenericInStaticField;

	private List<T> list;

	public UseGenericInClass() {
		list = new ArrayList<T>();

	}

	public void add(T o) {
		list.add(o);
	}

	public T getValue() {
		return list.get(0);
	}

	public static void main(String[] args) {
		UseGenericInClass<String> useGenericInClass = new UseGenericInClass<String>();
		// useGenericInClass.add(1);// 由于Generic类型被声明为了String，这种类型是不合法的。
		useGenericInClass.add("test1");
		useGenericInClass.add("test2");

		System.out.println(useGenericInClass.getValue());
	}

}
