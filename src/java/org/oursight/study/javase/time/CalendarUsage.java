package org.oursight.study.javase.time;

import java.util.Calendar;

public class CalendarUsage {
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTimeZone().toString());
	}

}
