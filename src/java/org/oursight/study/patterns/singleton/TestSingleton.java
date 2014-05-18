package org.oursight.study.patterns.singleton;

import junit.framework.TestCase;

public class TestSingleton extends TestCase {

	public void testSingleton() {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		assertSame(s1, s2);
	}

	/**
	 * LazySingleton这种实现是有问题的！
	 * @author yaonengjun,2011-3-24 下午08:40:21
	 */
	public void testLazySingleton() {
		LazySingleton ls1 = LazySingleton.getInstance();
		LazySingleton ls2 = LazySingleton.getInstance();
		System.out.println("ls1: " + ls1);
		System.out.println("ls2: " + ls2);
		assertSame(ls1, ls2);

	}

}
