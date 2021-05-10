package com.okmillet.regulate.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static final String dt = "yyyy-MM-dd HH:mm:ss";
	public static final String d = "yyyy-MM-dd";

	public static String dTS(Date d) {
		if(d == null) {
			return "";
		}
		return new SimpleDateFormat(dt).format(d);
	}

	public static String intTS(Integer intTime) {
		if(intTime == null) {
			return "";
		}
		Date d = new Date(intTime.longValue()*1000);
		return new SimpleDateFormat(dt).format(d);
	}

	public static String intTDS(Integer intTime) {
		if(intTime == null) {
			return "";
		}
		Date date = new Date(intTime.longValue()*1000);
		return new SimpleDateFormat(d).format(date);
	}

	public static Integer sTInt(String s) {
		if(s == null) {
			return null;
		}
		String[] sArr = s.split("-");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MILLISECOND, 0);
		int year = 0, month = 0, date = 0, hourOfDay = 0, minute = 0, second = 0;
		year = Integer.valueOf(sArr[0]);
		month = Integer.valueOf(sArr[1]);
		if(sArr[2].length()>2) {
			String[] dtArr = sArr[2].split(" ");
			date = Integer.valueOf(dtArr[0]);
			String[] tArr = dtArr[1].split(":");
			if(tArr.length==3) {
				hourOfDay = Integer.valueOf(tArr[0]);
				minute = Integer.valueOf(tArr[1]);
				second = Integer.valueOf(tArr[2]);
			}else if(tArr.length==2) {
				hourOfDay = Integer.valueOf(tArr[0]);
				minute = Integer.valueOf(tArr[1]);
			}else if(tArr.length==1) {
				hourOfDay = Integer.valueOf(tArr[0]);
			}
		}else {
			date = Integer.valueOf(sArr[2]);
		}
		c.set(year, month-1, date, hourOfDay, minute, second);
		return (int)(c.getTimeInMillis()/1000);
	}

}
