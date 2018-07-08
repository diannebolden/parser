package com.ef.parser.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ef.parser.domain.entity.AccessLog;
import com.ef.parser.domain.entity.DailyIp;
import com.ef.parser.domain.entity.HourlyIp;

public interface ParserService {
	public void saveAllHourlyIp(List<HourlyIp> hourlyIps);
	public void saveAllDailyIp(List<DailyIp> dailyIps);
	
	public List<AccessLog> findIpsByDateRangeAndThreshold(@Param("startDate") Date startDate,
														  @Param("duration") int duration,
														  @Param("threshold") int threshold);

}
