package com.ef.parser.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;



public class DateUtils {
	public static final String YEAR_MONTH_DATETIME ="yyyy-MM-dd.HH:mm:ss";
	
	private DateUtils() {
		// Pure utility class, do NOT instantiate.
	}
	
	public static Date parseSqlDate(String sqlDateStr) throws ParseException {
		Date sqlDate = null;
		if (!StringUtils.isBlank(sqlDateStr)) {
			SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_DATETIME);
			java.util.Date date = null;
			
			date = sdf.parse(sqlDateStr);
			sqlDate = new Date(date.getTime()); 
		}
		return sqlDate;
	}
}
