package org.oursight.study.patterns.singleton;

/**
 * 单例模式的标准实现。在JVM加载类的时候即构建类的实例。<br>
 * 与Singleton模式对应的，是Monostate模式。
 * @author yaonengjun,2011-3-24 下午08:10:20
 *
 */
public class Singleton {
	
	private static Singleton theInstance = new Singleton();
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		return theInstance;
	}

}
