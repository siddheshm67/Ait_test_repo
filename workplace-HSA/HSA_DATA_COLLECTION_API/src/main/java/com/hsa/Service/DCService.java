package com.hsa.Service;

import org.springframework.stereotype.Service;

import com.hsa.DTO.KidsInfoDto;
import com.hsa.DTO.SummeryDto;
import com.hsa.Entity.Education;
import com.hsa.Entity.Income;

@Service
public interface DCService {

	public boolean saveEduDetails(Education education);
	public boolean saveIncomeDetails(Income income);
	public boolean saveKidsDetails(KidsInfoDto kids);
	
	public SummeryDto getDetailsSummery(Integer AppId);
}
