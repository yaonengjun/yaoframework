package org.oursight.study.javase.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 从普通的ArrayList实现中，随机获取其中的值
 * @author yaonengjun,2012-3-13 上午08:56:09
 *
 */
public class RandomList {
	
	public static void main(String[] str) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			list.add(i);
		}
		System.out.println("orginal List:");
		for (Integer s : list) {
			System.out.print(s);
		}
		System.out.println();

		System.out.println("after shuffle List:");
		for (int i = 1; i <= 10; i++) {
			Collections.shuffle(list);
			System.out.print(i + ": ");
			for (Integer s : list) {
				System.out.print(s);
			}
			System.out.println();
		}
		
		
	}
}
