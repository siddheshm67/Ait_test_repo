package com.spring.project.service;

import com.spring.project.Binding.DashboardResponse;
import com.spring.project.entity.Conunsellor;

public interface CounsellorService {
	
	public String saveCounseller(Conunsellor conunsellor);
	
	public Conunsellor loginCheck(String email,String pwd);
	
	public boolean recoverPwd(String email);
	
	public DashboardResponse getDashboardInfo(Integer id);
	

}
