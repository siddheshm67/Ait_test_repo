package com.hsa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsa.DTO.KidsInfoDto;
import com.hsa.DTO.SummeryDto;
import com.hsa.Entity.Education;
import com.hsa.Entity.Income;
import com.hsa.Entity.Kid;
import com.hsa.Repo.EducationRepo;
import com.hsa.Repo.IncomeRepo;
import com.hsa.Repo.KidsRepo;

@Service
public class DCServiceImpl implements DCService{
	
	@Autowired
	private EducationRepo educationRepo;
	@Autowired
	private IncomeRepo incomeRepo;
	@Autowired
	private KidsRepo kidsRepo;

	@Override
	public boolean saveEduDetails(Education education) {
		
		Education savedEducation = educationRepo.save(education);
			if (savedEducation.getEduID() != null) {
				return true;
			}
		return false;
	}

	@Override
	public boolean saveIncomeDetails(Income income) {
		Income savedIncome = incomeRepo.save(income);
		if (savedIncome.getIncomeId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean saveKidsDetails(KidsInfoDto kids) {
		List<Kid> kidsList = kids.getKids();
		List<Kid> savedKids = kidsRepo.saveAll(kidsList);
		if (savedKids != null) {
			return true;
		}
		return false;
	}

	@Override
	public SummeryDto getDetailsSummery(Integer AppId) {
		
		SummeryDto summeryDto = new SummeryDto();
		summeryDto.setEducation(educationRepo.findByCitizenAppId(AppId));
		summeryDto.setIncome(incomeRepo.findByCitizenAppId(AppId));
		
		List<Kid> kids = kidsRepo.findByCitizenAppId(AppId);
		KidsInfoDto kidsInfoDto = new KidsInfoDto();
		kidsInfoDto.setKids(kids);
		summeryDto.setKidsInfo(kidsInfoDto);
		
		return summeryDto;
	}

}
