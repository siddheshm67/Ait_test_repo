package com.hsa.ed.service;

import java.util.List;

import com.hsa.ed.entity.AppEligibilityInfo;

public interface ElegibilityService {
	
	public AppEligibilityInfo checkEligibility(Integer appNum);
	public List<AppEligibilityInfo> getEligibilityInfoByApp(Integer appNum);

}
