package com.hsa.dc.dto;

import java.util.List;

import com.hsa.dc.entity.Kid;

public class UserData {
	
	private Double salaryIncome;
	private Double propertyIncome;
	
	private String univercityName;
	private String higestDegree;
	
	private List<Kid> kids;

	public Double getSalaryIncome() {
		return salaryIncome;
	}

	public void setSalaryIncome(Double salaryIncome) {
		this.salaryIncome = salaryIncome;
	}

	public Double getPropertyIncome() {
		return propertyIncome;
	}

	public void setPropertyIncome(Double propertyIncome) {
		this.propertyIncome = propertyIncome;
	}

	public String getUnivercityName() {
		return univercityName;
	}

	public void setUnivercityName(String univercityName) {
		this.univercityName = univercityName;
	}

	public String getHigestDegree() {
		return higestDegree;
	}

	public void setHigestDegree(String higestDegree) {
		this.higestDegree = higestDegree;
	}

	public List<Kid> getKids() {
		return kids;
	}

	public void setKids(List<Kid> kids) {
		this.kids = kids;
	}
	
	

}
