package com.playcrab.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;

public class DateUtils {
	private static final String dateFormat1 = "yyyy-MM-dd";
	private static final String dateFormat2 = "yyyy-MM-dd HH:mm:ss";
	private static final String dateFormat3 = "yyyyMMdd";
	private static final String dateFormat4 = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String dateFormat5 = "yyyy/MM/dd";
	private static final String dateFormat6 = "yyyyMMddHHmmss";
	private static final String dateFormat7 = "yyMMdd";
	private static final String dateFormat8 = "yyyyMMdd";
	private static final String dateFormat9 = "yyyyMM";
	private static final String dateFormat10 = "yyyy-MM-dd HH:mm";

	public static long getSecond() {
		Calendar calendar = Calendar.getInstance();
		long second = calendar.getTimeInMillis() / 1000;
		return second;
	}

	public static String formatDate1(Date date) {
		return DateFormatUtils.format(date, dateFormat1);
	}

	public static String formatDate2(Date date) {
		return DateFormatUtils.format(date, dateFormat2);
	}

	public static String formatDate2(Date date, String timeZone) {
		// TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
		// return DateFormatUtils.format(date, dateFormat2);
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat2);
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdf.format(date);
	}

	public static String formatDate3(Date date) {
		return DateFormatUtils.format(date, dateFormat3);
	}

	public static String formatDate4(Date date) {
		return DateFormatUtils.format(date, dateFormat4);
	}

	public static String formatDate5(Date date) {
		return DateFormatUtils.format(date, dateFormat5);
	}

	public static String formatDate6(Date date) {
		return DateFormatUtils.format(date, dateFormat6);
	}

	public static String formatDate7(Date date) {
		return DateFormatUtils.format(date, dateFormat7);
	}

	public static String formatDate8(Date date) {
		return DateFormatUtils.format(date, dateFormat8);
	}

	public static String formatDate9(Date date) {
		return DateFormatUtils.format(date, dateFormat9);
	}

	public static String formatDate10(Date date) {
		return DateFormatUtils.format(date, dateFormat10);
	}

	public static Date parseDate(String date, String timeZone) throws ParseException {
		SimpleDateFormat sm = new SimpleDateFormat(dateFormat2);
		sm.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sm.parse(date);
	}

	public static String dateTransformBetweenTimeZone(Date sourceDate, TimeZone sourceTimeZone, TimeZone targetTimeZone) {
		Long targetTime = sourceDate.getTime() - sourceTimeZone.getRawOffset() + targetTimeZone.getRawOffset();
		return getTime(new Date(targetTime), new SimpleDateFormat(dateFormat2));
	}

	public static Date parseDate(String sourceDate, TimeZone sourceTimeZone, TimeZone targetTimeZone) throws ParseException {
		Long targetTime = parseDate(sourceDate).getTime() - sourceTimeZone.getRawOffset() + targetTimeZone.getRawOffset();
		return new Date(targetTime);
	}
	


	public static String getTime(Date date, DateFormat formatter) {
		return formatter.format(date);
	}

	//
	public static Date parseDate(String date) throws ParseException {
		return org.apache.commons.lang.time.DateUtils.parseDate(date, new String[] { dateFormat1, dateFormat2, dateFormat3, dateFormat4, dateFormat5,
				dateFormat6, dateFormat10 });
	}

}
