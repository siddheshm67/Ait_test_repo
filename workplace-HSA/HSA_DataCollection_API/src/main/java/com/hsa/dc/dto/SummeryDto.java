package com.hsa.dc.dto;

import com.hsa.dc.entity.Education;
import com.hsa.dc.entity.Income;

public class SummeryDto {
	
	private Income income;
	private Education education;
	private KidsDto kidsDto;
	
	private Integer appNum;

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public KidsDto getKidsDto() {
		return kidsDto;
	}

	public void setKidsDto(KidsDto kidsDto) {
		this.kidsDto = kidsDto;
	}

	public Integer getAppNum() {
		return appNum;
	}

	public void setAppNum(Integer appNum) {
		this.appNum = appNum;
	}
	
	
	
}
