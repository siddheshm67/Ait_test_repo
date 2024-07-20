package com.hsa.AppRegister.service;

import java.util.List;

import com.hsa.AppRegister.Entity.CitizenApp;

public interface AppService {
	
	public Integer createApplication(CitizenApp citizenApp);
	public CitizenApp getApp(Integer appNum);
	public List<CitizenApp> getApps();
	
	

}
