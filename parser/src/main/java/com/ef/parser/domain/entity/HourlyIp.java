package com.ef.parser.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity 
public class HourlyIp implements Serializable {
	private static final long serialVersionUID = -7402431507723682631L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter @Setter private Long id;
    @Getter @Setter private String ip;
    @Getter @Setter private String blockingReason;
    
	public HourlyIp(String ip, String blockingReason) {
		this.ip = ip;
		this.blockingReason = blockingReason;
	}
}	

