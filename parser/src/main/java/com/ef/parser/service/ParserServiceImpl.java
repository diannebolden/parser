package com.ef.parser.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ef.parser.domain.entity.AccessLog;
import com.ef.parser.domain.entity.DailyIp;
import com.ef.parser.domain.entity.HourlyIp;
import com.ef.parser.repository.AccessLogRepository;
import com.ef.parser.repository.DailyIpRepository;
import com.ef.parser.repository.HourlyIpRepository;

@Service
public class ParserServiceImpl implements ParserService {
	@Autowired
	HourlyIpRepository hourlyRepository;
	
	@Autowired
	DailyIpRepository dailyRepository;
	
	@Autowired
	AccessLogRepository accessLogRepository;
	

	@Override
	public List<HourlyIp> findByIP(String ip) {
		return this.hourlyRepository.findByIp(ip);
	}

	@Override
	public void saveAllHourlyIp(List<HourlyIp> hourlyIps) {
		this.hourlyRepository.saveAll(hourlyIps);
	}
	@Override
	public void saveAllDailyIp(List<DailyIp> dailyIps) {
		this.dailyRepository.saveAll(dailyIps);
	}

	@Override
	public List<AccessLog> findIpsByDateRangeAndThreshold(Date startDate, int duration, int threshold) {
		return this.accessLogRepository.findByDateRangeAndThreshold(startDate, duration, threshold);
	}

	
}
