package com.hsa.AppRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsa.DTO.KidsInfoDto;
import com.hsa.DTO.SummeryDto;
import com.hsa.Entity.Education;
import com.hsa.Entity.Income;
import com.hsa.Entity.Kid;
import com.hsa.Service.DCService;

@RestController
public class DCAppRestController {

	@Autowired
	private DCService dcService;
	
	@PostMapping("/income")
	public ResponseEntity<String> saveIncomeDetails(@RequestBody Income income){
		boolean isDetailsSaved = dcService.saveIncomeDetails(income);
		if (isDetailsSaved) {
			return new ResponseEntity<String>("Details saved",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Error !!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/education")
	public ResponseEntity<String> saveEducationDetails(@RequestBody Education education){
		boolean isDetailsSaved = dcService.saveEduDetails(education);
		if (isDetailsSaved) {
			return new ResponseEntity<String>("Details saved",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Error !!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/kids")
	public ResponseEntity<String> saveKidsDetails(@RequestBody KidsInfoDto kids){
		boolean isDetailsSaved = dcService.saveKidsDetails(kids);
		if (isDetailsSaved) {
			return new ResponseEntity<String>("Details saved",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Error !!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/summery/{id}")
	public ResponseEntity<SummeryDto> getSummery(@PathVariable("id") Integer id){
		SummeryDto detailsSummery = dcService.getDetailsSummery(id);
		if (detailsSummery != null) {
			return new ResponseEntity<SummeryDto>(detailsSummery,HttpStatus.OK);
		}else {
			return new ResponseEntity<SummeryDto>(detailsSummery,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
