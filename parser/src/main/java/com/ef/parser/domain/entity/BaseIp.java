package com.ef.parser.domain.entity;


import lombok.Getter;
import lombok.Setter;

public class BaseIp {
	private static final String BLOCKING_REASON ="Request threshold exceeded.";
	

    @Getter @Setter private String ip;
    @Getter private String blockingReason=BLOCKING_REASON;
    
    public BaseIp (String ip) {
    	this.ip = ip;
    }
}
