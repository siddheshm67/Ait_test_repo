package com.ssn.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssn.api.entity.CitizenInfo;
import com.ssn.api.entity.DataEntity;
import com.ssn.api.service.DataService;

@RestController
public class DataRestController {
	
	@Autowired
	private DataService dataService;
	
	@PostMapping("/ssa")
	public ResponseEntity<CitizenInfo> getStateName(@RequestBody DataEntity dataEntity) {
	    CitizenInfo citizenInfo = new CitizenInfo();	
	    String ssnState = dataService.getSsnState(dataEntity.getSsnNumber());
	    citizenInfo.setState(ssnState);
	    citizenInfo.setSsnNumber(dataEntity.getSsnNumber());
	    
	    return new ResponseEntity<>(citizenInfo,HttpStatus.OK);  
	}

}
