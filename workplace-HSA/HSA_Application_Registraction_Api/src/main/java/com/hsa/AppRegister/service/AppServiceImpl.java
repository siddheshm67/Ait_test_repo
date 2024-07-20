package com.hsa.AppRegister.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hsa.AppRegister.DTO.SSARequest;
import com.hsa.AppRegister.DTO.SSAResponse;
import com.hsa.AppRegister.Entity.CitizenApp;
import com.hsa.AppRegister.Repo.AppRepo;

@Service
public class AppServiceImpl implements AppService {

	@Autowired
	private AppRepo appRepo;

	@Value("${SSAWebApiUrl}")
	private String SSAwebUrl;

	private Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

	@Override
	public Integer createApplication(CitizenApp citizenApp) {

		logger.info("application creation started...");
		RestTemplate restTemplate = new RestTemplate();
		logger.info("getting ssn details from url : " + SSAwebUrl);

		SSARequest ssaRequest = new SSARequest();
		ssaRequest.setFullName(citizenApp.getName());
		ssaRequest.setDob(citizenApp.getDob());
		ssaRequest.setSsnNumber(citizenApp.getSsn().toString());

		// ResponseEntity<SSAResponse> sSaData = restTemplate.postForEntity(SSAwebUrl,
		// SSAResponse.class, ssaRequest);

		ResponseEntity<SSAResponse> sSaData = restTemplate.postForEntity(SSAwebUrl, ssaRequest, SSAResponse.class);

		if (sSaData.getBody().getState().equalsIgnoreCase("rhode island")) {
			logger.info("USER SSN verification successful...");
			CitizenApp savedApp = appRepo.save(citizenApp);
			logger.info("Application created for user...");
			return savedApp.getAppNum();
		}

		logger.info("USER SSN verification fail ...");

		return 0;
	}

	@Override
	public CitizenApp getApp(Integer appNum) {
		logger.info("getting application data for AppID: " + appNum);
		CitizenApp app = appRepo.findById(appNum).orElseThrow();
		return app;
	}

	@Override
	public List<CitizenApp> getApps() {
		logger.info("getting all applications data");
		return appRepo.findAll();
	}

}
