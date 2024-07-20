package com.hsa.ed.DTO;

import java.util.List;

public class UserData {
	
	private Double salaryIncome;
	private Double propertyIncome;
	
	private String univercityName;
	private String higestDegree;
	
	private List<ApplicantKid> kids;

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

	public List<ApplicantKid> getKids() {
		return kids;
	}

	public void setKids(List<ApplicantKid> kids) {
		this.kids = kids;
	}
	
	

}
