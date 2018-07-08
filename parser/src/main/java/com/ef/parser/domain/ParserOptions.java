package com.ef.parser.domain;

import java.sql.Date;

import com.ef.parser.util.validators.ParserOptionsValidator;

import lombok.Getter;
import lombok.Setter;

public class ParserOptions {
 public static final int HOURLY = 1;//1 hour
 public static final int DAILY = 24;//24 hours
 public static final int MAX_HOURLY_THRESHOLD = 200;//200 requests per hour
 public static final int MAX_DAILY_THRESHOLD = 500;//500 requests per day
 public static final String START_DATE = "startDate";
 public static final String THRESHOLD = "threshold";
 public static final String DURATION = "duration";
 
 @Getter @Setter private Date startDate; //Format:yyyy-MM-dd HH:mm:ss
 @Getter @Setter private int duration;  //Valid values: 1, 24
 @Getter @Setter private int threshold; //Min value 1, Max value 200 for duration =1, Max value 500 for duration =24
	
	public ParserOptions(final Date startDate,
						final int duration,
						final int threshold) {
		if (ParserOptionsValidator.isValid(duration, threshold)){
	        this.startDate = startDate;
	        this.duration = duration;
	        this.threshold = threshold;
		}else {
			throw new IllegalArgumentException();
		}
    }
	
}
