package org.oursight.study.javase.collections;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * 对字符数组的一些处理方法试验
 * 
 * @author yaonengjun
 * 
 */
public class StringArray {

	/**
	 * 将String数组中的内容按照字母表升序排列
	 */
	public static void resortStringArray() {
		String[] strArray = { "bb", "ab", "ac", "bc", "c" };

		Set s = new TreeSet();
		s.addAll(Arrays.asList(strArray));
		System.out.println(s);
	}
	
	/**
	 * ？对于中文数组，没有看出来是按照什么排列的
	 */
	public static void resortChineseStringArray() {
		String[] strArray = { "中", "华", "人", "民", "共" ,"和", "国" };

		Set s = new TreeSet();
		s.addAll(Arrays.asList(strArray));
		System.out.println(s);

	}
	
	public static void main(String[] args) {
		//resortStringArray();
		resortChineseStringArray();
	}

}
