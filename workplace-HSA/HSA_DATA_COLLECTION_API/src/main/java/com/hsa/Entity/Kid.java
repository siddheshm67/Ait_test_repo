package com.hsa.Entity;

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
	private LocalDate kidDob;
	private String kidSSN;
	
	private Integer citizenAppId;

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
		return kidDob;
	}

	public void setKidDob(LocalDate kidDob) {
		this.kidDob = kidDob;
	}

	public String getKidSSN() {
		return kidSSN;
	}

	public void setKidSSN(String kidSSN) {
		this.kidSSN = kidSSN;
	}

	public Integer getCitizenAppId() {
		return citizenAppId;
	}

	public void setCitizenAppId(Integer citizenAppId) {
		this.citizenAppId = citizenAppId;
	}
	
	
}
