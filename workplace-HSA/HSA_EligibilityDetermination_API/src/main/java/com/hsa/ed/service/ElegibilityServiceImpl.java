package com.hsa.ed.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hsa.ed.DTO.ApplicantKid;
import com.hsa.ed.DTO.CitizenApp;
import com.hsa.ed.DTO.UserData;
import com.hsa.ed.Repo.AeligibilityInfoRepo;
import com.hsa.ed.entity.AppEligibilityInfo;

@Service
public class ElegibilityServiceImpl implements ElegibilityService {

	@Autowired
	private AeligibilityInfoRepo eInfoRepo;

	private Logger logger = LoggerFactory.getLogger(ElegibilityServiceImpl.class);

	@Override
	public AppEligibilityInfo checkEligibility(Integer appNum) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserData> userDataResponse = restTemplate
				.getForEntity("http://localhost:8083/userdata/" + appNum, UserData.class);
		UserData userData = userDataResponse.getBody();

		// get citizen from citizen app

		ResponseEntity<CitizenApp> citizenApp = restTemplate.getForEntity("http://localhost:8082/app/" + appNum,
				CitizenApp.class);

		// get plan name from plan no

		ResponseEntity<String> planName = restTemplate
				.getForEntity("http://localhost:8081/planName/" + citizenApp.getBody().getPlanId(), String.class);

		AppEligibilityInfo appEligibilityInfo = eInfoRepo
				.save(checkElegibilityForPlan(appNum, userData, citizenApp, planName));
		return appEligibilityInfo;
	}

	@Override
	public AppEligibilityInfo getEligibilityInfoByApp(Integer appNum) {
		AppEligibilityInfo appEligibilityInfo = eInfoRepo.findByAppNum(appNum);
		return appEligibilityInfo;
	}

	private AppEligibilityInfo checkElegibilityForPlan(Integer appNum, UserData userData,
			ResponseEntity<CitizenApp> citizenApp, ResponseEntity<String> planName) {
		AppEligibilityInfo appEligibilityInfo = new AppEligibilityInfo();
		appEligibilityInfo.setAppNum(appNum);
		appEligibilityInfo.setPlanName(planName.getBody());

		switch (planName.getBody().toUpperCase()) {
		case "SNAP": {
			appEligibilityInfo = checkForSNAP(appNum, userData, planName, appEligibilityInfo);
			break;
		}
		case "CCAP": {
			appEligibilityInfo = checkForCCAP(appNum, userData, planName, appEligibilityInfo);
			break;

		}
		case "MEDICADE": {
			appEligibilityInfo = checkForMedicade(appNum, userData, planName, appEligibilityInfo);
			break;

		}
		case "MEDICARE": {
			appEligibilityInfo = checkForMedicare(citizenApp, appEligibilityInfo);
			break;

		}
		case "RIW": {
			appEligibilityInfo = checkForRIW(userData, appEligibilityInfo);
			break;
		}
		default:
			appEligibilityInfo.setPlanStatus("invalid plan selected");
			logger.error("no matching value found for plan name");
		}
		return appEligibilityInfo;
	}

	private AppEligibilityInfo checkForRIW(UserData userData, AppEligibilityInfo appEligibilityInfo) {
		if (userData.getSalaryIncome() == 00.00 && userData.getPropertyIncome() == 00.00) {

			if (!userData.getHigestDegree().equalsIgnoreCase("10")
					&& !userData.getHigestDegree().equalsIgnoreCase("12")) {
				appEligibilityInfo.setBenifitAmt(250.00);
				appEligibilityInfo.setDenialReason("NA");
				appEligibilityInfo.setStartDate(LocalDate.now());
				appEligibilityInfo.setEndDate(LocalDate.now().plusYears(3));
				appEligibilityInfo.setPlanStatus("APPROVED");
			} else {
				appEligibilityInfo.setDenialReason("citizen should be graduated");
				appEligibilityInfo.setPlanStatus("REJECTED");
			}
		} else {
			appEligibilityInfo.setDenialReason("citizen income should be zero");
			appEligibilityInfo.setPlanStatus("REJECTED");
		}
		return appEligibilityInfo;
	}

	private AppEligibilityInfo checkForMedicare(ResponseEntity<CitizenApp> citizenApp,
			AppEligibilityInfo appEligibilityInfo) {
		LocalDate citizenDobDate = citizenApp.getBody().getDob();
		int citizenAge = Period.between(citizenDobDate, LocalDate.now()).getYears();
		if (citizenAge < 60) {
			appEligibilityInfo.setBenifitAmt(550.00);
			appEligibilityInfo.setDenialReason("NA");
			appEligibilityInfo.setStartDate(LocalDate.now());
			appEligibilityInfo.setEndDate(LocalDate.now().plusYears(5));
			appEligibilityInfo.setPlanStatus("APPROVED");
		} else {
			appEligibilityInfo.setDenialReason("citizen age should be less then 60");
			appEligibilityInfo.setPlanStatus("REJECTED");
		}

		return appEligibilityInfo;
	}

	private AppEligibilityInfo checkForMedicade(Integer appNum, UserData userData, ResponseEntity<String> planName,
			AppEligibilityInfo appEligibilityInfo) {
		if (userData.getSalaryIncome() <= 300) {
			if (userData.getPropertyIncome() == 0) {
				appEligibilityInfo.setBenifitAmt(450.00);
				appEligibilityInfo.setDenialReason("NA");
				appEligibilityInfo.setStartDate(LocalDate.now());
				appEligibilityInfo.setEndDate(LocalDate.now().plusYears(4));
				appEligibilityInfo.setPlanStatus("APPROVED");
			} else {
				appEligibilityInfo.setDenialReason("property income should be zero");
				appEligibilityInfo.setPlanStatus("REJECTED");

			}
		}else {
			appEligibilityInfo.setDenialReason("Salary income is greater then 300$");
			appEligibilityInfo.setPlanStatus("REJECTED");
		}
		return appEligibilityInfo;
	}

	private AppEligibilityInfo checkForCCAP(Integer appNum, UserData userData, ResponseEntity<String> planName,
			AppEligibilityInfo appEligibilityInfo) {
		List<ApplicantKid> kids = userData.getKids();
		if (userData.getSalaryIncome() <= 300) {
			if (kids.size() > 0) {

				List<Integer> kidsAgeList = new ArrayList<>();
				kids.forEach(e -> {
					LocalDate kidDob = e.getKidDob();
					int kidAge = Period.between(kidDob, LocalDate.now()).getYears();
					kidsAgeList.add(kidAge);
				});

				if (kidsAgeList.stream().allMatch(e -> e < 16)) {
					appEligibilityInfo.setBenifitAmt(700.00);
					appEligibilityInfo.setDenialReason("NA");
					appEligibilityInfo.setStartDate(LocalDate.now());
					appEligibilityInfo.setEndDate(LocalDate.now().plusYears(5));
					appEligibilityInfo.setPlanStatus("APPROVED");
				} else {
					appEligibilityInfo.setDenialReason("Kid age is greater then 16");
					appEligibilityInfo.setPlanStatus("REJECTED");
				}

			} else {
				appEligibilityInfo.setDenialReason("No kids of applicant");
				appEligibilityInfo.setPlanStatus("REJECTED");
			}
		} else {
			appEligibilityInfo.setDenialReason("Salary income is greater then 300$");
			appEligibilityInfo.setPlanStatus("REJECTED");

		}

		return appEligibilityInfo;
	}

	private AppEligibilityInfo checkForSNAP(Integer appNum, UserData userData, ResponseEntity<String> planName,
			AppEligibilityInfo appEligibilityInfo) {
		if (userData.getSalaryIncome() <= 300) {
			appEligibilityInfo.setBenifitAmt(380.00);
			appEligibilityInfo.setDenialReason("NA");
			appEligibilityInfo.setStartDate(LocalDate.now());
			appEligibilityInfo.setEndDate(LocalDate.now().plusYears(3));
			appEligibilityInfo.setPlanStatus("APPROVED");
		} else {
			appEligibilityInfo.setDenialReason("Salary income is greater then 300$");
			appEligibilityInfo.setPlanStatus("REJECTED");

		}
		return appEligibilityInfo;
	}

}
