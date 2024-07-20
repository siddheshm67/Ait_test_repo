package com.hsa.ed.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hsa.ed.entity.AppEligibilityInfo;
import com.hsa.ed.service.ElegibilityService;

@RestController
public class EdRestController {

	@Autowired
	ElegibilityService elegibilityService;

	@GetMapping("/check/{appNum}")
	public ResponseEntity<AppEligibilityInfo> checkPlanElegibility(@PathVariable("appNum") Integer appNum) {

		AppEligibilityInfo appEligibilityInfo = elegibilityService.checkEligibility(appNum);

		return new ResponseEntity<AppEligibilityInfo>(appEligibilityInfo, HttpStatus.OK);

	}

}
