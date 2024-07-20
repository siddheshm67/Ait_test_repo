package com.hsa.plans.RestController;

import java.util.List;

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

import com.hsa.plans.Dto.PlanDTO;
import com.hsa.plans.service.PlanService;

@RestController
public class PlanRestController {

	@Autowired
	private PlanService planService;

	private org.slf4j.Logger logger = LoggerFactory.getLogger(PlanRestController.class);

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody PlanDTO planDTO) {
		logger.info("plan saveing started...");
		boolean savePlan = planService.savePlan(planDTO);
		if (savePlan) {
			logger.info("plan saved in db");
			return new ResponseEntity<String>("plan Saved", HttpStatus.CREATED);
		} else {
			logger.error("error in saving plan");
			return new ResponseEntity<String>("failed to save", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/plans")
	public ResponseEntity<List<PlanDTO>> getPlans() {
		logger.info("getting list of plans");
		List<PlanDTO> plans = planService.getPlans();
		return new ResponseEntity<List<PlanDTO>>(plans, HttpStatus.OK);
	}

	@GetMapping("/plan/{id}")
	public ResponseEntity<PlanDTO> getPlan(@PathVariable("id") Integer planId) {
		logger.info("getting plan by id : " + planId);
		PlanDTO plan = planService.getPlan(planId);
		return new ResponseEntity<PlanDTO>(plan, HttpStatus.OK);
	}

	@PutMapping("/plan/{id}/{activeSwitch}")
	public ResponseEntity<String> updatePlan(@PathVariable("id") Integer id,
			@PathVariable("activeSwitch") String activeSwitch) {
		logger.info("updating plan status..");
		boolean isPlanUpdated = planService.updatePlan(id, activeSwitch);
		if (isPlanUpdated) {
			logger.info("plan status updated to : " + activeSwitch);
			return new ResponseEntity<String>("plan updated", HttpStatus.OK);
		} else {
			logger.error("error updating plan status");
			return new ResponseEntity<String>("failed to update", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/planName/{id}")
	public ResponseEntity<String> getPlanName(@PathVariable("id") Integer planId) {
		logger.info("getting plan name by id : " + planId);
		PlanDTO plan = planService.getPlan(planId);
		return new ResponseEntity<String>(plan.getPlanName(), HttpStatus.OK);
	}
	

}
