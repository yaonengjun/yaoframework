package org.oursight.study.javase.jdk15.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 不使用Generic的时候，存在的问题
 * @author yaonengjun,2011-3-27 上午09:43:27
 *
 */
public class NotUseGeneric {
	
	public static void main(String[] args) {
		NotUseGeneric notUse = new NotUseGeneric();
		notUse.wrongUseGeneric();
	}
	
	public void notUseGeneric() {
		List listOfString = new ArrayList();
		listOfString.add("Test");
		
		Integer value = (Integer)listOfString.get(0);
	}
	
	public void wrongUseGeneric() {
		List list = new ArrayList();
		list.add(3);
		list.add("String");
		
		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			String str = it.next();
			
		}
	}
	

}
