package com.hsa.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hsa.enitity.ApplicationResponse;
import com.hsa.enitity.SSNReposnse;
import com.hsa.enitity.UserApplication;

@Service
public class ApplicationServiceImpl implements ApplicationService {


	@Override
	public ApplicationResponse checkEligability(UserApplication userApplication) {
		String eligabilityStatus;
		String URL = "http://54.160.157.69:8080/ssa";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<SSNReposnse> ssnEntity = restTemplate.postForEntity(URL, userApplication, SSNReposnse.class);
		String state = ssnEntity.getBody().getState();
		if ("rhode island".equals(state)) {
			eligabilityStatus = "ELIGABLE";
		}else {
			eligabilityStatus = "NOTELIGABLE";
		}
	
		return new ApplicationResponse(eligabilityStatus,state);
	}

}
