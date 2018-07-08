package com.ef.parser.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity 
public class DailyIp implements Serializable {
	private static final long serialVersionUID = 2977332733537878101L;
	private static String BLOCKING_REASON = "IP {0} exceeded the daily {1} requests threshold limit during the specified timeframe.";
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter @Setter private Long id;
    @Getter @Setter private String ip;
    @Getter private String blockingReason=BLOCKING_REASON;
    
    
    public DailyIp(String ip, String blockingReason) {
		this.ip = ip;
		this.blockingReason = blockingReason;
	}
}	

