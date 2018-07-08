package com.ef.parser.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ef.parser.util.validators.IpValidator;

import lombok.Getter;
import lombok.Setter;

@Entity 
public class DailyIp implements Serializable {
	private static final long serialVersionUID = 2977332733537878101L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter @Setter private Long id;
    @Getter @Setter private String ip;
    @Getter private String blockingReason;
    
    
    public DailyIp(String ip, String blockingReason) {
    	if(IpValidator.isIpValid(ip)) {
			this.ip = ip;
			this.blockingReason = blockingReason;
    	}else {
    		throw new IllegalArgumentException();
    	}
	}
}	

