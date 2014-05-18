package org.oursight.study.javase.jdk15.generic;

/**
 * 通过继承，限定了Generic参数的类型
 * @author yaonengjun,2011-3-27 上午11:16:12
 *
 */
public class UseGenericInExtend <N extends Number> extends UseGenericInClass<N> {

	public static void main(String[] args) {
		UseGenericInExtend<Integer> u = new UseGenericInExtend<Integer>();
//		UseGenericInExtend<String> uStr = new UseGenericInExtend<String>(); // 非法

	}

}
