package com.hsa.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsa.main.Repo.PlanRepo;
import com.hsa.main.entity.PlanDto;
import com.hsa.main.entity.PlanEntity;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepo planRepo;

	@Override
	public List<PlanDto> getAllPlans() {
		List<PlanEntity> list = planRepo.findAll();
		List<PlanDto> plansDtoList = new ArrayList<>();
		
		list.forEach(entity -> {
			PlanDto dto = new PlanDto();
			BeanUtils.copyProperties(entity,dto);
			plansDtoList.add(dto);
		});
		
		return plansDtoList;
	}

	@Override
	public boolean createNewPlan(PlanDto planDto) {
		PlanEntity planEntity = new PlanEntity();
		BeanUtils.copyProperties(planDto, planEntity);
		planEntity.setPlanStatus("Y");
		PlanEntity entity = planRepo.save(planEntity);
		return entity.getPlanId() != null;
	}

	@Override
	public PlanDto getPlan(Integer planId) {
		PlanEntity entity = planRepo.findById(planId).orElseThrow();
		PlanDto planDto = new PlanDto();
		BeanUtils.copyProperties(entity, planDto);
		return planDto;
	}

	@Override
	public boolean updatePlan(Integer planId, String status) {
		PlanEntity entity = planRepo.findById(planId).orElseThrow();
		entity.setPlanStatus(status);
		PlanEntity isSaved = planRepo.save(entity);
		return isSaved.getPlanId() !=null;
	}

	
	
	

}
