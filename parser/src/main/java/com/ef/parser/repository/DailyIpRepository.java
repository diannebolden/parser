package com.ef.parser.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ef.parser.domain.entity.DailyIp;

public interface DailyIpRepository extends CrudRepository<DailyIp,Long> {
	
	List<DailyIp> findByIp(String ip);
 

}