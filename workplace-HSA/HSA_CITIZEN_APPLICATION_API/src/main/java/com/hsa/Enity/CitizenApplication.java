package com.hsa.Enity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CITIZEN_APPLICATION_MASTER")
public class CitizenApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer citizenAppId;
	private String citizenName;
	private LocalDate citizenDob;
	private String citizenGender;
	private String citizenSSN;
	private Integer planId;
	private Integer citizenId;
	
	
	public Integer getCitizenId() {
		return citizenId;
	}
	public void setCitizenId(Integer citizenId) {
		this.citizenId = citizenId;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	
	
	public Integer getCitizenAppId() {
		return citizenAppId;
	}
	public void setCitizenAppId(Integer citizenAppId) {
		this.citizenAppId = citizenAppId;
	}
	public String getCitizenName() {
		return citizenName;
	}
	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}
	public LocalDate getCitizenDob() {
		return citizenDob;
	}
	public void setCitizenDob(LocalDate citizenDob) {
		this.citizenDob = citizenDob;
	}
	public String getCitizenGender() {
		return citizenGender;
	}
	public void setCitizenGender(String citizenGender) {
		this.citizenGender = citizenGender;
	}
	public String getCitizenSSN() {
		return citizenSSN;
	}
	public void setCitizenSSN(String citizenSSN) {
		this.citizenSSN = citizenSSN;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	
	
	
	
	

	
	
}
