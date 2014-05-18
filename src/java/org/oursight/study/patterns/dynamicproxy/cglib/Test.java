package org.oursight.study.patterns.dynamicproxy.cglib;

import org.oursight.study.patterns.dynamicproxy.ISubject;
import org.oursight.study.patterns.dynamicproxy.Subject;

public class Test {
	
	public static void main(String[] args) {
		CglibProxy proxy = new CglibProxy();
		ISubject subjectProxy = (ISubject)proxy.getProxy(Subject.class);
		subjectProxy.setName("AAA");
	}

}
