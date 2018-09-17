/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wind.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Title: DateUtil
 * @Package com.wind.common.util
 * @Description: 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author wind
 * @date 2018/9/17 18:06
 * @version V1.0
 */
public class DateUtil{

	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**********************************************日期时间常量************************************************/

	public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_STR = "yyyy-MM-dd";

	public static final String DATE_STR2 = "yyyyMMdd";

	public static final String DATETIME_MS = "yyyyMMddHHmmssSSS";

	public static final String DATE_SLASH_STR = "yyyy/MM/dd";

	public static final String MONTH_STR = "yyyyMM";

	public static final int SECOND = 1000;

	public static final int MINUTE = 60 * SECOND;

	public static final int HOUR = 60 * MINUTE;

	public static final int DAY = 24 * HOUR;

	/**
	 * 格式化字符串日期，转换成Date
	 * @param date  字符串日期
	 * @param pattern 默认 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parseDate(String date, String pattern){
		pattern = pattern == null ? DATE_TIME : pattern;
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Date d = null;
		try {
			d = dateFormat.parse(date);
		} catch (ParseException e) {
			logger.warn("parse date string error. date:" + date + ", pattern:" + pattern, e);
		}
		return d;
	}

	/**
	 * 日期按照指定格式转换成字符串
	 * @param date  日期
	 * @param pattern 默认 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDate(Date date, String pattern){
		pattern = pattern == null ? DATE_TIME : pattern;
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	/**
	 * 计算两个日期之间的天数差
	 * @param start
	 * @param end
	 * @return
	 */
	public static Long daysBetween(Date start, Date end){
		return daysBetween(start.getTime(), end.getTime());
	}

	/**
	 * 计算两个日期之间的天数差
	 * @param start
	 * @param end
	 * @return
	 */
	public static Long daysBetween(Long start, Long end){
		if(end == null || start == null){
			return 0L;
		}
		return (end - start)/DAY;
	}


	/**
	 * 获取星期
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 获取前几个月或者后几个月
	 * @param date
	 * @param i  i > 0 ，返回值为i个月之后,否则则为之前
	 * @return
	 */
	public static Date getMonth(Date date, int i){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, i);
		return c.getTime();
	}

	/**
	 * 获取前几个小时或者后几个小时
	 * @param date
	 * @param i i  i > 0 ，返回值为i个小时之后,否则则为之前
	 * @return
	 */
	public static Date getHour(Date date, int i){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, i);
		return c.getTime();
	}

	/**
	 * 获取当前月的某一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMonthFDay(int year, int month){
		Calendar c = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		if(month < 10){
			sb.append(0);
		}
		sb.append(month);
		Date date = parseDate(sb.toString(), MONTH_STR);
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/**
	 * 获取当前月的某一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMonthEDay(int year, int month){
		Calendar c = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		if(month < 10){
			sb.append(0);
		}
		sb.append(month);
		Date date = parseDate(sb.toString(), MONTH_STR);
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 获取日期 时分秒毫秒清零
	 * @param date
	 * @return
	 */
	public static Date getDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// 将时分秒,毫秒域清零
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取年
	 * @param date
	 * @return
	 */
	public static int getYear(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取月
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 判断当前时间是上午，还是下午
	 * @param date
	 * @return
	 */
	public static boolean isAm(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		return hour <= 12;
	}

	/**
	 * 比较日期相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equals(Date a, Date b){
		if(a == null){
			return false;
		}

		if(b == null){
			return false;
		}
		return a.getTime() == b.getTime();
	}

	/**
	 * 计算两个日期之间的年份
	 * @param sTime
	 * @param eTime
	 * @return
	 */
	public static List<String> years(Date sTime, Date eTime){
		List<String> list = new ArrayList<>();
		Calendar s = Calendar.getInstance();
		s.setTime(sTime);
		int sYear = s.get(Calendar.YEAR);
		Calendar e = Calendar.getInstance();
		e.setTime(eTime);
		int eYear = e.get(Calendar.YEAR);
		for(int i = sYear; i <= eYear; i++){
			list.add(String.valueOf(i));
		}
		return list;
	}

	/**
	 * 计算两个月之间的月数
	 * @param sTime
	 * @param eTime
	 * @return
	 */
	public static List<Date> months(Date sTime, Date eTime){
		List<Date> dates = new ArrayList<>();
		Calendar s = Calendar.getInstance();
		Date startTime = getDate(sTime);
		s.setTime(startTime);
		s.set(Calendar.DAY_OF_MONTH, 1);
		dates.add(startTime);

		Calendar e = Calendar.getInstance();
		Date endTime = getDate(eTime);
		e.setTime(endTime);
		e.set(Calendar.DAY_OF_MONTH, 1);
		while(startTime.before(e.getTime())){
			s.add(Calendar.MONTH, 1);
			startTime = s.getTime();
			dates.add(startTime);
		}
		return dates;
	}

	/**
	 * 计算两个日期之间的天数
	 * @param sTime
	 * @param eTime
	 * @return
	 */
	public static List<Date> days(Date sTime, Date eTime){
		List<Date> dates = new ArrayList<>();
		Calendar s = Calendar.getInstance();
		Date startTime = getDate(sTime);
		s.setTime(startTime);
		dates.add(startTime);

		Date endTime = getDate(eTime);
		while(startTime.before(endTime)){
			s.add(Calendar.DAY_OF_YEAR, 1);
			startTime = s.getTime();
			dates.add(startTime);
		}
		return dates;
	}

	/**
	 * 获取精确秒的时间戳
	 * @param date
	 * @return
	 */
	public static Integer getSecondTime(Date date){
		if(date != null){
			try {
				return Long.valueOf(date.getTime() / SECOND).intValue();
			} catch (Exception e) {
				logger.error("transfer timestamp error", e);
			}
		}
		return 0;
	}
}
