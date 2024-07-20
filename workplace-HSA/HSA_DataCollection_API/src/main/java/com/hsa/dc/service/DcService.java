package com.hsa.dc.service;

import com.hsa.dc.dto.KidsDto;
import com.hsa.dc.dto.SummeryDto;
import com.hsa.dc.dto.UserData;
import com.hsa.dc.entity.Education;
import com.hsa.dc.entity.Income;

public interface DcService {
	
	public boolean saveIncome(Income income);
	public boolean saveEducation(Education education);
	public boolean saveKids(KidsDto kids);
	public SummeryDto getSummery(Integer appNum);
	public UserData getUserDataBYAppNo(Integer appNum);

}
