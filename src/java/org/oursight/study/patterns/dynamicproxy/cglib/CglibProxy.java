package org.oursight.study.patterns.dynamicproxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	private Enhancer enhancer = new Enhancer();

	public Object getProxy(Class clazz) {
		enhancer.setSuperclass(clazz); // 设置需要创建子类的类
		enhancer.setCallback(this);
		return enhancer.create(); // 通过字节码技术动态创建子类实例
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// PerformanceMonitor.begin(obj.getClass().getName() + "." + method.getName());
		System.out.println(obj.getClass().getName() + "." + method.getName() + " begin");
		Object result = proxy.invokeSuper(obj, args); // 通过代理类调用父类中的方法
		// PerformanceMonitor.end();
		System.out.println(obj.getClass().getName() + "." + method.getName() + " end /n");
		return result;
	}
}
