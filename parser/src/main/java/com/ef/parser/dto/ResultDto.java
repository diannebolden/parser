package com.ef.parser.dto;

import java.text.MessageFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ResultDto {
	private static String BLOCKING_REASON = "This IP made {0} requests during the specified timeframe. Exceeding the maximum threshold.";
	
	
	@Getter @Setter private String ip;
	@Getter @Setter private int numberRequests;

	public String getBlockingReason() {
		return MessageFormat.format(BLOCKING_REASON, numberRequests);
	}
}
