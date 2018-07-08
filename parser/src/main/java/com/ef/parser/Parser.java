package com.ef.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ef.parser.domain.ParserOptions;
import com.ef.parser.domain.entity.AccessLog;
import com.ef.parser.domain.entity.DailyIp;
import com.ef.parser.domain.entity.HourlyIp;
import com.ef.parser.service.ParserService;
import com.ef.parser.util.OptionRetriever;

@SpringBootApplication
@ComponentScan({"com.ef.parser",
				"com.ef.parser.controller",
				"com.ef.parser.entity",
				"com.ef.parser.repository",
				"com.ef.parser.util",
				"com.ef.parser.service"
				}
)
public class Parser implements CommandLineRunner{
	private static final String BLOCKING_REASON = "IP %s exceeded the hourly %d requests threshold limit during the specified timeframe.";
	private static final String IP_EXCEEDING_STR = "IPs exceeding %d requests threshold:";

	private @Autowired ParserService parserService;
	private @Autowired OptionRetriever optionRetriever;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Parser.class);
		app.run(args);
	}
	
    @Override
    public void run(String... args) throws IOException, URISyntaxException, ParseException {
    	ParserOptions parserOptions = this.optionRetriever.retrieveOptions(args);
    	
    	if (parserOptions != null) {
	    	List<AccessLog> ipsExceedingLimit =this.parserService.findIpsByDateRangeAndThreshold(
	    			parserOptions.getStartDate(),
	    			parserOptions.getDuration(),
	    			parserOptions.getThreshold());
	    	this.handleResults(ipsExceedingLimit, parserOptions);
    	}
    } 
    
    private void handleResults(List<AccessLog> ipsExceedingLimit, ParserOptions parserOptions) {
    	int numIpsExceedingLimit = ipsExceedingLimit.size();
    	List<HourlyIp>hourlyIps = new ArrayList<HourlyIp>(numIpsExceedingLimit);
    	List<DailyIp>dailyIps =  new ArrayList<DailyIp>(numIpsExceedingLimit);

    	System.out.println(String.format(IP_EXCEEDING_STR, parserOptions.getThreshold()));
    	for (AccessLog accessLog:ipsExceedingLimit) {
    		String accessLogIp = accessLog.getIp();
    		System.out.println(accessLogIp);
    		
    		if (parserOptions.getDuration() == ParserOptions.HOURLY) {
    			hourlyIps.add(new HourlyIp(accessLogIp,
  					  String.format(BLOCKING_REASON, accessLogIp, parserOptions.getThreshold())));
    		}else {
    			dailyIps.add(new DailyIp(accessLogIp,
    					  String.format(BLOCKING_REASON, accessLogIp, parserOptions.getThreshold())));
    		}
    		
    	}
    	if (parserOptions.getDuration() == ParserOptions.HOURLY) {
    		this.parserService.saveAllHourlyIp(hourlyIps);
    	}else {
    		this.parserService.saveAllDailyIp(dailyIps);
    	}
    }
}
