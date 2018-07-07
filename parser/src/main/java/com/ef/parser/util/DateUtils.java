package com.ef.parser.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
	public static final String YEAR_MONTH_DATETIME ="yyyy-MM-dd.HH:mm:ss";
	
	public static Date parseSqlDate(String sqlDateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_DATETIME);
		java.util.Date date = null;
		try {
			date = sdf.parse(sqlDateStr);
		} catch (ParseException e) {
			// TODO Add Logging
			e.printStackTrace();
		}
		Date sqlDate = new Date(date.getTime()); 
		return sqlDate;
	}
}
