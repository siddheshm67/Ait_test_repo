package com.hsa.service;

import com.hsa.enitity.ApplicationResponse;
import com.hsa.enitity.UserApplication;

public interface ApplicationService {
	
	public ApplicationResponse checkEligability(UserApplication userApplication);	

}
