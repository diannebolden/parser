package com.ef.parser.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ef.parser.domain.entity.AccessLog;

public interface AccessLogRepository extends CrudRepository<AccessLog,Long> {
	
	List<AccessLog> findByIp(String ip);
	
	@Query(value = "SELECT access_id, ip "
            + "FROM access_log "
            + "where access_date BETWEEN :startDate and DATE_ADD(:startDate, INTERVAL :duration HOUR) "
            + "GROUP BY ip "
            + "having count(*) >=:threshold "
            + "ORDER BY ip", nativeQuery=true)

    List<AccessLog> findByDateRangeAndThreshold(@Param("startDate") Date startDate,
    											@Param("duration") int duration,
    											@Param("threshold") int threshold);
}
