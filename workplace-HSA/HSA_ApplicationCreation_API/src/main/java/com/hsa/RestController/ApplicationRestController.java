package com.hsa.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsa.enitity.ApplicationResponse;
import com.hsa.enitity.UserApplication;
import com.hsa.service.ApplicationService;

@RestController
public class ApplicationRestController {
	
	@Autowired
	ApplicationService applicationService;
	
	@PostMapping("/check")
	public ApplicationResponse getEligibilityStatus(@RequestBody UserApplication userApplication) {
	
		 return applicationService.checkEligability(userApplication);
	
	}

}
