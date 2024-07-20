package com.hsa.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsa.Enity.CitizenApplication;
import com.hsa.service.CitizenService;
import com.hsa.util.MailUtil;
import com.hsa.util.PasswordUtil;

@RestController
public class CitizenRestController {

	@Autowired
	private CitizenService citizenService;
	
	@PostMapping("/citizenApplication")
	public ResponseEntity<String> createApplication(@RequestBody CitizenApplication citizen){
		boolean isAccountCreated = citizenService.createNewCitizenApplication(citizen);
		if (isAccountCreated) {
			return new ResponseEntity<String>("application created",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("invalid credentials",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/citizenApplications")
	public ResponseEntity<List<CitizenApplication>> getAllCitizenApp(){
		List<CitizenApplication> allCitizenApps = citizenService.getAllCitizenApps();
		return new ResponseEntity<List<CitizenApplication>>(allCitizenApps,HttpStatus.OK);
	}
	
	@GetMapping("/citizenApplication/{id}")
	public ResponseEntity<CitizenApplication> getCitizenApplication(@PathVariable("id") Integer id ){
		CitizenApplication citizen = citizenService.getCitizenApplication(id);
		return new ResponseEntity<CitizenApplication> (citizen,HttpStatus.OK);
	}
	
	@GetMapping("/citizen/{id}")
	public ResponseEntity<CitizenApplication> getCitizenAppBYCitizenId(@PathVariable("id") Integer id ){
		CitizenApplication citizen = citizenService.getCitizenAppByCitizenID(id);
		return new ResponseEntity<CitizenApplication> (citizen,HttpStatus.OK);
	}
	
	
	
}
