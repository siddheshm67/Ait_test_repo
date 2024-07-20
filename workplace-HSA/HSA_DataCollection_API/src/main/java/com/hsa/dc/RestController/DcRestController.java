package com.hsa.dc.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsa.dc.dto.KidsDto;
import com.hsa.dc.dto.SummeryDto;
import com.hsa.dc.dto.UserData;
import com.hsa.dc.entity.Education;
import com.hsa.dc.entity.Income;
import com.hsa.dc.service.DcService;

@RestController
public class DcRestController {

	@Autowired
	private DcService dcService;

	@PostMapping("/income")
	public ResponseEntity<String> setIncome(@RequestBody Income income) {
		boolean saveIncome = dcService.saveIncome(income);
		if (saveIncome) {
			return new ResponseEntity<String>("income saved", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("error saving income", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/education")
	public ResponseEntity<String> setEducation(@RequestBody Education education) {
		boolean savedEducation = dcService.saveEducation(education);
		if (savedEducation) {
			return new ResponseEntity<String>("education saved", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("error saving education", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/kid")
	public ResponseEntity<String> setKids(@RequestBody KidsDto kidsDto) {
		boolean savedKids = dcService.saveKids(kidsDto);
		if (savedKids) {
			return new ResponseEntity<String>("kids saved", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("error saving kids", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/summery/{appNum}")
	public ResponseEntity<SummeryDto> getSummery(@PathVariable("appNum") Integer appNum) {
		SummeryDto summery = dcService.getSummery(appNum);
		return new ResponseEntity<SummeryDto>(summery, HttpStatus.OK);

	}
	
	@GetMapping("/userdata/{appNum}")
	public ResponseEntity<UserData> getUserData(@PathVariable ("appNum") Integer appNum ){
		UserData userData = dcService.getUserDataBYAppNo(appNum);
		return new ResponseEntity<UserData>(userData, HttpStatus.OK);
	}

}
