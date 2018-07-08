package com.ef.parser.repository;

import org.springframework.data.repository.CrudRepository;

import com.ef.parser.domain.entity.DailyIp;

public interface DailyIpRepository extends CrudRepository<DailyIp,Long> {

}