/*
 * Title: 	  TRS 身份服务器
 * Copyright: Copyright (c) 2004-2005, TRS信息技术有限公司. All rights reserved.
 * License:   see the license file.
 * Company:   TRS信息技术有限公司(www.trs.com.cn)
 * 
 * Created on 2005-5-11
 */
package org.oursight.framework.yao.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期和时间相关工具方法. <BR>
 * 
 * @author TRS信息技术有限公司
 * @version 1.0
 */
public class DateUtil {

	/**
	 * 将使用的毫秒数转化为可读的字符串, 如1天1小时1分1秒. <BR>
	 * <code>assertEquals("1天1小时1分1秒", DateUtil.timeToString(90061000));</code>
	 * 
	 * @param msUsed
	 *            使用的毫秒数.
	 * @return 可读的字符串, 如1天1小时1分1秒.
	 */
	public static String timeToString(long msUsed) {
		// [liushen] TODO 用移位运算提高性能.
		if (msUsed < 0) {
			return String.valueOf(msUsed);
		}
		if (msUsed < 1000) {
			return String.valueOf(msUsed) + "毫秒";
		}
		// 长于1秒的过程，毫秒不计
		msUsed /= 1000;
		if (msUsed < 60) {
			return String.valueOf(msUsed) + "秒";
		}
		if (msUsed < 3600) {
			long nMinute = msUsed / 60;
			long nSecond = msUsed % 60;
			return String.valueOf(nMinute) + "分" + String.valueOf(nSecond) + "秒";
		}
		// 3600 * 24 = 86400
		if (msUsed < 86400) {
			long nHour = msUsed / 3600;
			long nMinute = (msUsed - nHour * 3600) / 60;
			long nSecond = (msUsed - nHour * 3600) % 60;
			return String.valueOf(nHour) + "小时" + String.valueOf(nMinute) + "分" + String.valueOf(nSecond) + "秒";
		}

		long nDay = msUsed / 86400;
		long nHour = (msUsed - nDay * 86400) / 3600;
		long nMinute = (msUsed - nDay * 86400 - nHour * 3600) / 60;
		long nSecond = (msUsed - nDay * 86400 - nHour * 3600) % 60;
		return String.valueOf(nDay) + "天" + String.valueOf(nHour) + "小时" + String.valueOf(nMinute) + "分" + String.valueOf(nSecond) + "秒";
	}

	/**
	 * 取本周一.
	 * 
	 * @return 本周一
	 */
	public static Calendar getThisMonday() {
		return getThatMonday(Calendar.getInstance());
	}

	/**
	 * 获取cal所在周的周一.
	 * 
	 * @param cal
	 *            给定日期
	 * @return cal所在周的周一
	 */
	public static Calendar getThatMonday(Calendar cal) {
		int n = cal.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
		cal.add(Calendar.DATE, n);
		return cal;
	}

	/**
	 * 取本周日.
	 * 
	 * @return 本周日
	 */
	public static Calendar getThisSunday() {
		return getThatSunday(Calendar.getInstance());
	}

	/**
	 * 获取cal所在周的周日.
	 * 
	 * @param cal
	 *            给定日期
	 * @return cal所在周的周日
	 */
	public static Calendar getThatSunday(Calendar cal) {
		int n = (Calendar.SUNDAY + 7) - cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, n);
		return cal;
	}

	/**
	 * 获取两个日期相差的天数. <b>注意：</b> 此方法中的两个参数不存在先后的顺序关系，返回的结果会取绝对值，不会得到负数的返回值。
	 * 
	 * @return 两个日期相差的天数。只可能为零或者正数。
	 */
	public static int minus(Calendar cal1, Calendar cal2) {
		// int nBase = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) *
		// 365;
		// return Math.abs(nBase)
		// + Math.abs(cal1.get(Calendar.DAY_OF_YEAR)
		// - cal2.get(Calendar.DAY_OF_YEAR));

		// 改用新的计算日期差的算法，原来的算法在夸年时存在bug
		long time1 = cal1.getTimeInMillis();
		long time2 = cal2.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		between_days = Math.abs(between_days);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 从Date对象得到Calendar对象. <BR>
	 * JDK提供了Calendar.getTime()方法, 可从Calendar对象得到Date对象,
	 * 但没有提供从Date对象得到Calendar对象的方法.
	 * 
	 * @param date
	 *            给定的Date对象
	 * @return 得到的Calendar对象. 如果date参数为null, 则得到表示当前时间的Calendar对象.
	 */
	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		return cal;
	}

	/**
	 * 完成日期串到日期对象的转换. <BR>
	 * 
	 * @param dateString
	 *            日期字符串
	 * @param dateFormat
	 *            日期格式
	 * @return date 日期对象
	 */
	public static Date stringToDate(String dateString, String dateFormat) {
		if ("".equals(dateString) || dateString == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(dateFormat).parse(dateString);
		} catch (Exception e) {
			return null;
		}
	}

	public static String DateToString(Date date, String dateFormat) {
		if (date == null)
			return "";

		try {
			return new SimpleDateFormat(dateFormat).format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将 timeMillis 转成系统默认的时间格式（ yyyy-MM-dd HH:mm:ss）的方法
	 * 
	 * @param timeMillis
	 * @return
	 * @creator yaonengjun @ Jul 13, 2009
	 */
	public static String timeMillisToString(long timeMillis) {
		return timeMillisToString(timeMillis, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 完成timeMillis到日期类型的字符串的转换
	 * 
	 * @param timeMillis
	 *            以毫秒数记的long型的时间
	 * @param dateFormat
	 *            日期格式
	 * @return
	 */
	public static String timeMillisToString(long timeMillis, String dateFormat) {
		if ("".equals(dateFormat) || dateFormat == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(dateFormat).format(new Date(timeMillis));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取以系统默认的时间格式（ yyyy-MM-dd HH:mm:ss）展现的当前系统时间
	 * 
	 * @return
	 * @creator yaonengjun @ Jul 13, 2009
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获得以String表示的当前系统时间
	 * 
	 * @param dateFormat
	 * @return
	 * @creator yao.nengjun@trs.com.cn @ Apr 23, 2009
	 */
	public static String getCurrentDateTime(String dateFormat) {
		return timeMillisToString(System.currentTimeMillis(), dateFormat);
	}

	/**
	 * 获取和指定cal对象相隔指定天数的cal对象. 大于0表示之后, 小于0表之前.
	 * 
	 * @param cal
	 *            指定cal对象
	 * @param relativeDay
	 *            相隔指定天数
	 * @return cal对象
	 */
	public static Calendar getCalendar(Calendar cal, int relativeDay) {
		cal.add(Calendar.DATE, relativeDay);
		return cal;
	}

	/**
	 * 获取和当天相隔指定天数的Date对象. 大于0表示之后, 小于0表之前.
	 * 
	 * @param relativeDay
	 *            相隔指定天数
	 * @return Date对象
	 * @see #getCalendar(Calendar, int)
	 */
	public static Date getDate(int relativeDay) {
		return getCalendar(Calendar.getInstance(), relativeDay).getTime();
	}

	public static int month2second(int month) {
		return month * 30 * 24 * 3600;
	}
}
