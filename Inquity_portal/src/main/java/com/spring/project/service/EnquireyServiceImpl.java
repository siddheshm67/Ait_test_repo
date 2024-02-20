package com.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.spring.project.Binding.SearchCriteria;
import com.spring.project.Repo.StudentEnqRepo;
import com.spring.project.entity.StudentEnq;

@Service
public class EnquireyServiceImpl implements EnquieryService {
	
	
	@Autowired
	private StudentEnqRepo studentEnqRepo;

	@Override
	public boolean addEnq(StudentEnq studentEnq) {
		
		StudentEnq sEnq = studentEnqRepo.save(studentEnq);
		
		return sEnq.getEnqId()!=null;
	}

	@Override
	public List<StudentEnq> getEnquires(Integer id, SearchCriteria searchCriteria) {
		StudentEnq studentEnq = new StudentEnq();
		
		studentEnq.setCid(id);
		
		if(searchCriteria.getClassMode()!=null && !searchCriteria.getClassMode().equals("")) {
			studentEnq.setClassMode(searchCriteria.getClassMode());
		}
		
		if(searchCriteria.getCourseName()!=null && !searchCriteria.getCourseName().equals("")) {
			studentEnq.setCourseName(searchCriteria.getCourseName());
		}
		
		if(searchCriteria.getEnqStatus()!=null && !searchCriteria.getEnqStatus().equals("")) {
			studentEnq.setEnqStatus(searchCriteria.getEnqStatus());
		}
		
		Example<StudentEnq> example = Example.of(studentEnq);
		
		List<StudentEnq> enqs = studentEnqRepo.findAll(example);
		
		return enqs;
	}

}
