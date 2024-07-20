package com.hsa.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eduID;
	private String univercityName;
	private String graduteYear;
	private String higestDegree;
	
	private Integer citizenAppId;

	public Integer getEduID() {
		return eduID;
	}

	public void setEduID(Integer eduID) {
		this.eduID = eduID;
	}

	public String getUnivercityName() {
		return univercityName;
	}

	public void setUnivercityName(String univercityName) {
		this.univercityName = univercityName;
	}

	public String getGraduteYear() {
		return graduteYear;
	}

	public void setGraduteYear(String graduteYear) {
		this.graduteYear = graduteYear;
	}

	public String getHigestDegree() {
		return higestDegree;
	}

	public void setHigestDegree(String higestDegree) {
		this.higestDegree = higestDegree;
	}

	public Integer getCitizenAppId() {
		return citizenAppId;
	}

	public void setCitizenAppId(Integer citizenAppId) {
		this.citizenAppId = citizenAppId;
	}
	
	
	
}
