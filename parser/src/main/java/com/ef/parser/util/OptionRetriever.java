package com.ef.parser.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.springframework.stereotype.Component;

import com.ef.parser.domain.ParserOptions;

@Component
public class OptionRetriever {
	private static final Map<String, Integer> DURATION_MAP = createMap();
	private static Map<String, Integer> createMap() {

        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put("hourly", 1);
        result.put("daily", 24);
        return Collections.unmodifiableMap(result);
    }
	public ParserOptions retrieveOptions(String[] args) {
		Options options = new Options();
		// add t option
		options.addOption("threshold", true, "The number of requests limit");
		options.addOption("startDate", true, "The starting date.  Date Format: yyyy-MM-dd.HH:mm:ss");
		options.addOption("duration", true, "Duration of requests. Valid values are: hourly, daily ");
		CommandLineParser parser = new GnuParser();
		ParserOptions parserOptions = null;
		
		try {
			CommandLine cmd = parser.parse( options, args);
			parserOptions= new ParserOptions(
			DateUtils.parseSqlDate(cmd.getOptionValue("startDate")),
			DURATION_MAP.get(cmd.getOptionValue("duration")),
			Integer.valueOf(cmd.getOptionValue("threshold")).intValue());
			
		} catch (org.apache.commons.cli.ParseException e) {
			// TODO Add Logging
			e.printStackTrace();
		}
		return parserOptions;
	}
}
