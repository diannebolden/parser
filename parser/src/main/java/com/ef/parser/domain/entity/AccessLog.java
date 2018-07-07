package com.ef.parser.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="access_log")
public class AccessLog implements Serializable {
	private static final long serialVersionUID = 6538671963161569118L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="access_id")
    private Long id;
    
    @Column(length=25)
    private String ip;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}	

