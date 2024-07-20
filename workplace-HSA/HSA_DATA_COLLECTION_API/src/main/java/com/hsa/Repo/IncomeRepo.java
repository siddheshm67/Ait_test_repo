package com.hsa.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsa.Entity.Education;
import com.hsa.Entity.Income;
import java.util.List;


public interface IncomeRepo extends JpaRepository<Income, Integer> {
	
	Income findByCitizenAppId(Integer citizenAppId);

}
