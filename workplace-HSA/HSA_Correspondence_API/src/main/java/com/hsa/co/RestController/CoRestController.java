package com.hsa.co.RestController;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hsa.co.enity.CoEntity;
import com.hsa.co.service.CoService;

@RestController
public class CoRestController {

	@Autowired
	private CoService coService;
	
	@GetMapping("/notice/{appNum}")
	public ResponseEntity<CoEntity> getNoticesByAppNo(@PathVariable ("appNum") Integer appNum){
		CoEntity entity = coService.getNoticeByAppNo(appNum);
		return new ResponseEntity<CoEntity>(entity,HttpStatus.OK);
	}
	
	@GetMapping("/print/{appNum}")
	public String printNotice(@PathVariable ("appNum") Integer appNum) {
		
		try {
			coService.printNotices(appNum);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Printed....";
		
	}
}
