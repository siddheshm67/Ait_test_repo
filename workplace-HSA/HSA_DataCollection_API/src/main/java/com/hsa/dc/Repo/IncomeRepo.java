package com.hsa.dc.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsa.dc.entity.Education;
import com.hsa.dc.entity.Income;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Integer> {
	
	public Income findByAppNum(Integer appNum);

}
