package org.oursight.study.javase.extend;

/**
 * 这个类展示了，当存在继承关系时，父类的Clone方法返回的实际上是子类的对象。<br>
 * <b>因此，存在继承关系时（即一个类不是final的情况下），慎用clone方法</b>
 * @author yaonengjun,2010-12-7 下午05:49:32
 *
 */
public class CloneIsNotTrustableWhenExtendExisting {
	
	public static void main(String[] args) {
		Father f = new Son();
		try {
			System.out.println(f.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
