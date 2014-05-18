package org.oursight.study.patterns.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.oursight.study.patterns.dynamicproxy.ISubject;

public class JDKProxy implements InvocationHandler {
	
	private ISubject subject;
	
	public JDKProxy(ISubject subject) {
		this.subject = subject;
	}

	@Override
	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		System.out.println("before calling " + method);
        System.out.println("in calling:" + method.invoke(subject, args));
        System.out.println("after calling " + method);
        return null;
	}

	
}
