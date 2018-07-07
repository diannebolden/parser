package com.ef.parser.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

public class ParserOptions {
 private Date startDate; //Format:yyyy-MM-dd HH:mm:ss
 private int duration;  //Valid values: 1, 24
 private int threshold; //Min value 1, Max value 200 for duration =1, Max value 500 for duration =24
	
	public ParserOptions(final Date startDate,
						final int duration,
						final int threshold) {
//        validateStartDate(startDate);
//        validateDuration(duration);
//        validateThreshold(threshold);
        
        this.startDate = startDate;
        this.duration = duration;
        this.threshold = threshold;
    }
	//TODO Add Validation

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
}
