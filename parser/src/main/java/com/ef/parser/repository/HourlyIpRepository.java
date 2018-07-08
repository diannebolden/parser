package com.ef.parser.repository;

import org.springframework.data.repository.CrudRepository;

import com.ef.parser.domain.entity.HourlyIp;

public interface HourlyIpRepository extends CrudRepository<HourlyIp,Long> {

}