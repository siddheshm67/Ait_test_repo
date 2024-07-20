package com.hsa.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsa.Entity.Education;
import java.util.List;


public interface EducationRepo extends JpaRepository<Education, Integer>{

	Education findByCitizenAppId(Integer citizenAppId);
}
