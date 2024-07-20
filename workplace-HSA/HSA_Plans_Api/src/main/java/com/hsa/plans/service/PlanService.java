package com.hsa.plans.service;

import java.util.List;

import com.hsa.plans.Dto.PlanDTO;

public interface PlanService {
	
	public boolean savePlan(PlanDTO planDTO);
	
	public List<PlanDTO> getPlans();
	
	public PlanDTO getPlan(Integer planID);
	
	public boolean updatePlan(Integer planID,String status);
	

}
