package com.spring.project.service;

import java.util.List;

import com.spring.project.Binding.SearchCriteria;
import com.spring.project.entity.StudentEnq;

public interface EnquieryService {
	
	public boolean addEnq(StudentEnq studentEnq);
	
	public List<StudentEnq> getEnquires(Integer id,SearchCriteria searchCriteria);

}
