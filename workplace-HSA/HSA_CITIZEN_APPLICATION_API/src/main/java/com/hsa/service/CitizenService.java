package com.hsa.service;

import java.util.List;

import com.hsa.Enity.CitizenApplication;

public interface CitizenService {
	
	public boolean createNewCitizenApplication(CitizenApplication citizen);
	
	public CitizenApplication getCitizenApplication(Integer Id);
	
	public CitizenApplication getCitizenAppByCitizenID(Integer Id);
	
	public List<CitizenApplication> getAllCitizenApps();
	
	
	
	
}
