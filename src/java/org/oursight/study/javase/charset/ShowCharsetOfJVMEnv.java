package org.oursight.study.javase.charset;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 显示JVM的系统变量
 * 
 * @author yaonengjun@Jul 1, 2009
 * 
 */
public class ShowCharsetOfJVMEnv {

	public static void main(String[] args) {
		System.out.println("Hello, it's: " + new Date());
		// print available locales
		Locale list[] = DateFormat.getAvailableLocales();
		System.out.println("======System available locales:======== ");
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i].toString() + "\t" + list[i].getDisplayName());
		}
		// print JVM default properties
		System.out.println("======System property======== ");
		System.getProperties().list(System.out);
	}

}
