package org.oursight.study.patterns.singleton;

/**
 * Lazy单例模式的实现，仅在需要这个实例的时候，才创建该实例。<br>
 * 与Singleton模式对应的，是Monostate模式。
 * @author yaonengjun,2011-3-24 下午08:10:20
 *
 */
public class LazySingleton {
	
	private static LazySingleton theInstance = null;
	
	private LazySingleton() {
	}
	
	public static LazySingleton getInstance() {
		if(theInstance == null)
			theInstance = new LazySingleton();
		
		return theInstance;
	}

}
