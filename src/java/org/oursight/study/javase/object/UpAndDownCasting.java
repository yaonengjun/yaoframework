package org.oursight.study.javase.object;

import java.util.HashMap;
import java.util.Map;

public class UpAndDownCasting {
	
	public static void main(String[] args) {
		upcast();
//		othercast();
	}
	
	public static void upcast() {
		String str = new String("aaaa");
		Object strObj = (Object)str;
		System.out.println(strObj);
		System.out.println(strObj instanceof String);
		
		System.out.println("----------");
		
		String nullStr1 = null;
		Object nullStrObj1 = (Object)nullStr1;
		System.out.println(nullStr1);
		System.out.println(nullStrObj1 instanceof String);
		
		System.out.println();
		
		String nullStr2 = "";
		Object nullStrObj2 = (Object)nullStr2;
		System.out.println(nullStr2);
		System.out.println(nullStrObj2 instanceof String);
	}
	
	public static void othercast() {
		Map hashMap = new HashMap();
		Object hashMapObj = (Object)hashMap;
		String aaa = (String)hashMapObj;
		System.out.println(aaa);
	}

}
