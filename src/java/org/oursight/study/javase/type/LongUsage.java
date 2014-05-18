package org.oursight.study.javase.type;

import com.trs.idm.util.DateUtil;

public class LongUsage {
	
	public static void main(String[] args) {
		whatEndDayIsIfWeUseLongAsTimeMills();
	}
	
	public static void whatEndDayIsIfWeUseLongAsTimeMills() {
//		String max = new Long(Long.MAX_VALUE).toString();
		System.out.println("Max: " + Long.MAX_VALUE);
		System.out.println("Current TimeMills: " + System.currentTimeMillis());
		System.out.println("The End of Time is: " + DateUtil.timeMillisToString(Long.MAX_VALUE));
	}

}
