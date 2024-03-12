package com.hsa.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsa.main.Repo.PlanRepo;
import com.hsa.main.entity.PlanEntity;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepo planRepo;
	
	@Override
	public List<PlanEntity> getAllPlans() {
		return planRepo.findAll();
	}

	@Override
	public PlanEntity createNewPlan(PlanEntity planEntity) {
		return planRepo.save(planEntity);
	}

}
