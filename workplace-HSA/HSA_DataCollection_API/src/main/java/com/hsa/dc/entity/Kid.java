package com.hsa.dc.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Kid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer kidId;
	private String kidName;
	private LocalDate KidDob;
	private long kidSSn;
	
	private Integer appNum;

	public Integer getKidId() {
		return kidId;
	}

	public void setKidId(Integer kidId) {
		this.kidId = kidId;
	}

	public String getKidName() {
		return kidName;
	}

	public void setKidName(String kidName) {
		this.kidName = kidName;
	}

	public LocalDate getKidDob() {
		return KidDob;
	}

	public void setKidDob(LocalDate kidDob) {
		KidDob = kidDob;
	}

	public long getKidSSn() {
		return kidSSn;
	}

	public void setKidSSn(long kidSSn) {
		this.kidSSn = kidSSn;
	}

	public Integer getAppNum() {
		return appNum;
	}

	public void setAppNum(Integer appNum) {
		this.appNum = appNum;
	}
	

}
