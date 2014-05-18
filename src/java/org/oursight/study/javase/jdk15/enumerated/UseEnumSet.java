package org.oursight.study.javase.jdk15.enumerated;

import java.util.EnumSet;

/**
 * 使用EnumSet将Enum中的值分组
 * @author yaonengjun,2011-3-27 下午12:33:10
 *
 */
public class UseEnumSet {
	
	public static void main(String[] args) {
		EnumSet enumSet = EnumSet.of(EnumAsClass.BUSY, EnumAsClass.ERROR);
		System.out.println(enumSet.contains(EnumAsClass.BUSY));
		System.out.println(enumSet.contains(EnumAsClass.WAIT));
		System.out.println(enumSet.contains("BUSY"));
	}
	

}
