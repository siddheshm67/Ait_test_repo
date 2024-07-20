package com.hsa.dc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsa.dc.Repo.EducationRepo;
import com.hsa.dc.Repo.IncomeRepo;
import com.hsa.dc.Repo.KidsRepo;
import com.hsa.dc.dto.KidsDto;
import com.hsa.dc.dto.SummeryDto;
import com.hsa.dc.dto.UserData;
import com.hsa.dc.entity.Education;
import com.hsa.dc.entity.Income;
import com.hsa.dc.entity.Kid;

@Service
public class DcServiceImpl implements DcService {
	
	@Autowired
	private IncomeRepo incomeRepo;
	
	@Autowired
	private EducationRepo educationRepo;
	
	@Autowired
	private KidsRepo kidsRepo;

	@Override
	public boolean saveIncome(Income income) {
		Income savedIncome = incomeRepo.save(income);
		return savedIncome.getIncomeId() != null;
	}

	@Override
	public boolean saveEducation(Education education) {
		Education savedEducation = educationRepo.save(education);
		return savedEducation.getEduId() != null;
	}

	@Override
	public boolean saveKids(KidsDto kids) {
		List<Kid> kidsList = kids.getKids();
		List<Kid> savedKids = kidsRepo.saveAll(kidsList);
		return !savedKids.isEmpty();
	}

	@Override
	public SummeryDto getSummery(Integer appNum) {
		
		Income userImcome = incomeRepo.findByAppNum(appNum);
		Education userEducation = educationRepo.findByAppNum(appNum);
		List<Kid> kidsList = kidsRepo.findByAppNum(appNum);
		
		SummeryDto summeryDto = new SummeryDto();
		
		summeryDto.setAppNum(appNum);
		summeryDto.setIncome(userImcome);
		summeryDto.setEducation(userEducation);
		
		KidsDto kidsDto = new KidsDto();
		kidsDto.setAppNum(appNum);
		kidsDto.setKids(kidsList);
		summeryDto.setKidsDto(kidsDto);
		
		return summeryDto;
	}

	@Override
	public UserData getUserDataBYAppNo(Integer appNum) {
		
		UserData userData = new UserData();
		
		Income userImcome = incomeRepo.findByAppNum(appNum);
		userData.setSalaryIncome(userImcome.getSalaryIncome());
		userData.setPropertyIncome(userImcome.getPropertyIncome());
		
		Education userEducation = educationRepo.findByAppNum(appNum);
		userData.setUnivercityName(userEducation.getUnivercityName());
		userData.setHigestDegree(userEducation.getHigestDegree());
		
		List<Kid> kidsList = kidsRepo.findByAppNum(appNum);
		userData.setKids(kidsList);
		
		return userData;
	}

}
