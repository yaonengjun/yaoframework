package org.oursight.study.patterns.monostate;

/**
 * Monostate的实现，核心在于定义了一个private的static变量，在JVM加载类的时候，就创建了该变量，这样所有的Monostate类，实际上使用的变量都是JVM加载类时创建的这个。
 * 
 * @author yaonengjun,2011-3-24 下午09:10:41
 *
 */
public class Monostate {
	
	private static int number = 0;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		Monostate.number = number;
	}
}
