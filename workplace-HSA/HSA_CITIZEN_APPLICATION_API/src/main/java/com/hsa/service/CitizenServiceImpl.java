package com.hsa.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hsa.Enity.CitizenApplication;
import com.hsa.Enity.UserApplicationDto;
import com.hsa.Repo.CitizenRepo;

@Service
public class CitizenServiceImpl implements CitizenService {

	@Autowired
	private CitizenRepo citizenRepo;
	
	@Value("${ssaurl}")
	private String SSA_API_URL;

	@Override
	public boolean createNewCitizenApplication(CitizenApplication citizen) {
		UserApplicationDto userApplicationDto = new UserApplicationDto();
		
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MMMM uuuu", Locale.ENGLISH);
        String dob = dtf.format(citizen.getCitizenDob());
		userApplicationDto.setDob(dob);
		
		userApplicationDto.setFullName(citizen.getCitizenName());
		userApplicationDto.setSsnNumber(citizen.getCitizenSSN());
		
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<com.hsa.Enity.SSNReposnse> ssnEntity = restTemplate.postForEntity(SSA_API_URL,userApplicationDto , com.hsa.Enity.SSNReposnse.class);
		String state = ssnEntity.getBody().getState();
		if ("rhode island".equals(state)) {
			citizenRepo.save(citizen);
			return true;
		}
		return false;
	}

	@Override
	public CitizenApplication getCitizenApplication(Integer appId) {
		CitizenApplication citizen = citizenRepo.findById(appId).orElseThrow();
		return citizen;
	}

	@Override
	public List<CitizenApplication> getAllCitizenApps() {
		return citizenRepo.findAll();
	}

	@Override
	public CitizenApplication getCitizenAppByCitizenID(Integer Id) {
		 CitizenApplication application = citizenRepo.findByCitizenId(Id);
		 return application;
		
	}

}
