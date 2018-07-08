package com.ef.parser.util;

import java.sql.Date;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ef.parser.domain.ParserOptions;

@Component
public class OptionRetriever {
	private static final String HELP_STR = "java -jar parser.jar --startDate=2017-01-01.15:00:00 --duration=hourly --threshold=200";
	private static final int MAX_HELP_WIDTH = 132;
	private static final Map<String, Integer> DURATION_MAP = createMap();
	private static Map<String, Integer> createMap() {

        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put("hourly", ParserOptions.HOURLY);
        result.put("daily", ParserOptions.DAILY);
        return Collections.unmodifiableMap(result);
    }
	private ParserOptions parserOptions = null;
	private HelpFormatter helpFormatter = new HelpFormatter();
	
	public ParserOptions retrieveOptions(String[] args) {
		helpFormatter.setWidth(MAX_HELP_WIDTH);
		
		Options options = new Options();

		options.addOption(ParserOptions.START_DATE, true, "starting date, date Format: yyyy-MM-dd.HH:mm:ss");
		options.addOption(ParserOptions.THRESHOLD, true, "request limit: 1 - 200 duration=hourly, 1 - 500 duration=daily");
		options.addOption(ParserOptions.DURATION, true, "duration of requests, valid values are: hourly, daily ");
		CommandLineParser parser = new GnuParser();
		
		try {
			CommandLine cmd = parser.parse( options, args);
			Date startDate = this.retrieveStartDate(cmd, helpFormatter, options);
			if (startDate != null) {
				Integer duration = this.retrieveDuration(cmd, helpFormatter, options);
				if (duration != null) {
					Integer threshold = this.retrieveThreshold(cmd, helpFormatter, options, duration);
					if (threshold != null) {
						parserOptions= new ParserOptions(
								startDate,
								duration.intValue(),
								threshold.intValue());
					}	
				}
			}
		} catch (org.apache.commons.cli.ParseException pe) {
			System.out.println("Command invalid.");
			helpFormatter.printHelp( HELP_STR, options );
		}

		return parserOptions;
	}

	private Date retrieveStartDate(CommandLine cmd, HelpFormatter formatter, Options options ) {
		Date startDate=null;

		if (!cmd.hasOption(ParserOptions.START_DATE) || StringUtils.isBlank(cmd.getOptionValue(ParserOptions.START_DATE))) {
			formatter.printHelp( HELP_STR, options);
		}else {
			try {
				startDate = DateUtils.parseSqlDate(cmd.getOptionValue(ParserOptions.START_DATE));
			}catch(ParseException pe) {
				System.out.println("Invaild date format");
				formatter.printHelp(HELP_STR, options );
			}
		}
		return startDate;
	}
	
	private Integer retrieveDuration(CommandLine cmd, HelpFormatter formatter, Options options) {
		Integer duration=null;
		if (!cmd.hasOption(ParserOptions.DURATION)) {
			formatter.printHelp( HELP_STR, options);
		}else {
			duration = DURATION_MAP.get(cmd.getOptionValue(ParserOptions.DURATION));
			if (duration == null) {
				formatter.printHelp( HELP_STR, options);
			}
		}
		return duration;
	}
	
	private Integer retrieveThreshold(CommandLine cmd, HelpFormatter formatter, Options options, Integer duration) {
		Integer threshold=null;
		if (!cmd.hasOption(ParserOptions.THRESHOLD)) {
			formatter.printHelp( HELP_STR, options);
		}else {
			try {
				Integer thresholdValue = Integer.valueOf(cmd.getOptionValue(ParserOptions.THRESHOLD));
				if (thresholdValue > 0 ) {
					if (duration.intValue() == ParserOptions.HOURLY ) {
						if (thresholdValue <= ParserOptions.MAX_HOURLY_THRESHOLD) {
							threshold = thresholdValue;
						}
					}else {
						if (thresholdValue <= ParserOptions.MAX_DAILY_THRESHOLD) {
							threshold = thresholdValue;
						}
					}
				}else {
					formatter.printHelp( HELP_STR, options);
				}
			}catch(Exception e) {
				formatter.printHelp( HELP_STR, options);
			}
			if (threshold == null) {
				formatter.printHelp( HELP_STR, options);
			}
		}
		return threshold;
	}
}
