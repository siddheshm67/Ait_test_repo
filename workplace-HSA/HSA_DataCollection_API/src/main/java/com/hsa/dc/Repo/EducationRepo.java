package com.hsa.dc.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsa.dc.entity.Education;
import java.util.List;

@Repository
public interface EducationRepo extends JpaRepository<Education, Integer>{
	
	public Education findByAppNum(Integer appNum);

}
