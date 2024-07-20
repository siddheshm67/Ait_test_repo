package com.hsa.AppRegister.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsa.AppRegister.Entity.CitizenApp;
import com.hsa.AppRegister.service.AppService;

@RestController
public class AppRestControllerr {
	
	@Autowired
	private AppService appService;
	
	@PostMapping("/app")
	public ResponseEntity< String> createApp(@RequestBody CitizenApp citizenApp){
		Integer applicationId = appService.createApplication(citizenApp);
		if (applicationId != 0) {
			return new ResponseEntity<String>("Application created with AppNo: "+ applicationId,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("error creating application",HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/app/{appNum}")
	public ResponseEntity<CitizenApp> getApplication(@PathVariable("appNum") Integer appNum){
		CitizenApp citizenApp = appService.getApp(appNum);
		return new ResponseEntity<CitizenApp>(citizenApp,HttpStatus.OK);
	}
	
	@GetMapping("/apps")
	public ResponseEntity<List<CitizenApp>> getApplication(){
		 List<CitizenApp> apps = appService.getApps();
		return new ResponseEntity<List<CitizenApp>>(apps,HttpStatus.OK);
	}
		
}
