package com.hsa.main.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsa.main.entity.PlanEntity;
import com.hsa.main.service.PlanService;

@RestController
public class PlanRestController {
	
	@Autowired
	private PlanService planService;
	
	@GetMapping("/plans")
	public List<PlanEntity> getAllPlans(){
		List<PlanEntity> plans = planService.getAllPlans();
		return plans;
	}
	
	@PostMapping("/plan")
	public PlanEntity createPlan(@RequestBody PlanEntity planEntity) {
		return planService.createNewPlan(planEntity);
	}
}
