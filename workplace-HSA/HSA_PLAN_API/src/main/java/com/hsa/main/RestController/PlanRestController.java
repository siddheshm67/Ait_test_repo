package com.hsa.main.RestController;


import java.util.List;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsa.main.entity.PlanDto;
import com.hsa.main.service.PlanService;

import ch.qos.logback.classic.Logger;



@RestController
public class PlanRestController {
	
	private org.slf4j.Logger  logger = LoggerFactory.getLogger(PlanRestController.class); 
	
	@Autowired
	private PlanService planService;
	
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody PlanDto planDto){
	logger.info("plan creation started");
		boolean isCreated = planService.createNewPlan(planDto);
		if (isCreated) {
			logger.info("plan saved");
			return new ResponseEntity<String>("plan saved successfully",org.springframework.http.HttpStatus.CREATED);
		}else {
			logger.error("error while saving plan");
			return new ResponseEntity<String>("failed to create plan",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<PlanDto>> getAllPlans(){
		logger.info("getting all plans ");
		List<PlanDto> plans = planService.getAllPlans();
		return new ResponseEntity<List<PlanDto>>(plans,HttpStatus.OK);
	}
	
	@GetMapping("/plan/{id}")
	public ResponseEntity<PlanDto> getPlan(@PathVariable("id") Integer id){
		logger.info("getting plan");
		PlanDto plan = planService.getPlan(id);
		return new ResponseEntity<PlanDto>(plan,HttpStatus.OK);
	}
	
	@PutMapping("/plan/{id}/{planStatus}")
	public ResponseEntity<String> upadatePlanStatus(@PathVariable ("id") Integer planId,@PathVariable("planStatus") String planStatus){
		logger.info("plan updation started");
		boolean isUpdated = planService.updatePlan(planId, planStatus);
		if (isUpdated) {
			logger.info("updated exiting plan ");
			return new ResponseEntity<String>("plan updated successfully",org.springframework.http.HttpStatus.OK);
		}else {
			logger.error("error while updating plan");
			return new ResponseEntity<String>("failed to update plan",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}



















