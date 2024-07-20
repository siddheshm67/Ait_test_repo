package com.hsa.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hsa.main.entity.PlanDto;
import com.hsa.main.entity.PlanEntity;

@Service
public interface PlanService {
	
	public List<PlanDto> getAllPlans();
	public boolean createNewPlan(PlanDto planDto);
	public PlanDto getPlan(Integer planId);
	public boolean updatePlan(Integer planId,String status);
	
	

}
