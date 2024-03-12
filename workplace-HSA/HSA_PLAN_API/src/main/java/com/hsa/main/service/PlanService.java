package com.hsa.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hsa.main.entity.PlanEntity;

@Service
public interface PlanService {
	
	public List<PlanEntity> getAllPlans();
	public PlanEntity createNewPlan(PlanEntity planEntity);
	

}
