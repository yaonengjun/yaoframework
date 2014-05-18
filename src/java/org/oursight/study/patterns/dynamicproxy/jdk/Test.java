package org.oursight.study.patterns.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.oursight.study.patterns.dynamicproxy.ISubject;
import org.oursight.study.patterns.dynamicproxy.Subject;

public class Test {

	public static void main(String[] args) {
		ISubject rs = new Subject(); // 在这里指定被代理类
		InvocationHandler ds = new JDKProxy(rs); // 初始化代理类
		Class cls = rs.getClass();

		// 以下是分解步骤
		/*
		 * 
		 * Class c = Proxy.getProxyClass(cls.getClassLoader(),cls.getInterfaces()) ;
		 * 
		 * Constructor ct=c.getConstructor(new Class[]{InvocationHandler.class});
		 * 
		 * Subject subject =(Subject) ct.newInstance(new Object[]{ds});
		 */

		// 以下是一次性生成
		ISubject subject = (ISubject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), ds);

		subject.request();
	}

}
