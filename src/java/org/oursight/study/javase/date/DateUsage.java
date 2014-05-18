package org.oursight.study.javase.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUsage {

	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))); 
		System.out.println(new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(System.currentTimeMillis()))); 
	}
}
