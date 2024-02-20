package com.spring.project.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.Binding.DashboardResponse;
import com.spring.project.Repo.CounsellorRepo;
import com.spring.project.Repo.StudentEnqRepo;
import com.spring.project.Util.EmailUtil;
import com.spring.project.entity.Conunsellor;
import com.spring.project.entity.StudentEnq;

@Service
public class CounsollerServiceImpl implements CounsellorService {

	@Autowired
	CounsellorRepo counsellorRepo;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	StudentEnqRepo studentEnqRepo;
	
	@Override
	public String saveCounseller(Conunsellor conunsellor) {
		Conunsellor obj = counsellorRepo.findByEmail(conunsellor.getEmail());
		if (obj != null) {
			return "email already exist";
		}
		
	   	Conunsellor conunsellorObj = counsellorRepo.save(conunsellor);
	   	if (conunsellorObj.getCid() != null) {
			return "registraction successfull";
		}
		
		return "registraction faild";
	}

	@Override
	public Conunsellor loginCheck(String email, String pwd) {
		return counsellorRepo.findByEmailAndPwd(email, pwd);
	}

	@Override
	public boolean recoverPwd(String email) {
		Conunsellor conunsellor = counsellorRepo.findByEmail(email);
		if (conunsellor==null) {
			return false;
		}
		String subjectString = "Recoverd password : ";
		String bodyString = "<h1> your password : " + conunsellor.getPwd() + "</h1>";
		return emailUtil.sendMail(subjectString, bodyString, email);
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer id) {
		
		List<StudentEnq> enquieryList =  studentEnqRepo.findByCid(id);
		
		Integer enrolloedEnqInteger = enquieryList.stream().filter(enqiery -> enqiery.getEnqStatus().equals("Enrolled")).collect(Collectors.toList()).size();
		
		DashboardResponse dashboardResponse = new DashboardResponse();
		dashboardResponse.setTotalEnq(enquieryList.size());
		dashboardResponse.setEnrolledEnq(enrolloedEnqInteger);
		dashboardResponse.setLostEnq(enquieryList.size()- enrolloedEnqInteger);
		
		return dashboardResponse;
	}

}
