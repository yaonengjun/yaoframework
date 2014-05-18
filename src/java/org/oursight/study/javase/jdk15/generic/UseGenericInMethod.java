package org.oursight.study.javase.jdk15.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UseGenericInMethod {

	public void useGenericInArg(List<String> list) {
		for (Iterator it = list.iterator(); it.hasNext();) {
			// String string = (Integer)it.next(); 此时会报错
			String string = (String) it.next();
		}

		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			// String string = (Integer)it.next(); 此时会报错
			String string = it.next();
		}

	}

	public List<String> getList() {
		List<Integer> intList = new ArrayList<Integer>();
		intList.add(1);

		List<String> stringList = new ArrayList<String>();
		stringList.add("aaa");

		// return intList;// 此时会报错
		return stringList;
	}

}
