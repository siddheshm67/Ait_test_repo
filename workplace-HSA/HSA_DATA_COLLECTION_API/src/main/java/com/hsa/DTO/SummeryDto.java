package com.hsa.DTO;

import com.hsa.Entity.Education;
import com.hsa.Entity.Income;

public class SummeryDto {
	
	private Education education;
	private Income income;
	private KidsInfoDto kidsInfo;
	
	private Integer citizenAppId;
	
	public KidsInfoDto getKidsInfo() {
		return kidsInfo;
	}

	public void setKidsInfo(KidsInfoDto kidsInfo) {
		this.kidsInfo = kidsInfo;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public Integer getCitizenAppId() {
		return citizenAppId;
	}

	public void setCitizenAppId(Integer citizenAppId) {
		this.citizenAppId = citizenAppId;
	}
	
	

}
