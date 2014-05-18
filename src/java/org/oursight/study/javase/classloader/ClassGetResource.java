package org.oursight.study.javase.classloader;

import java.util.HashMap;
import java.util.Map;

public class ClassGetResource {
	
	public static void main(String[] args) {
//		System.out.println("Class:" + java.util.HashMap.class.getResource("/User.hbm.xml"));
		Map map = new HashMap();
		try {
			System.out.println(Class.forName("java.lang.Thread"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
