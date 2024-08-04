package com.hsa.ed.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsa.ed.entity.AppEligibilityInfo;

@Repository
public interface AeligibilityInfoRepo extends JpaRepository<AppEligibilityInfo, Integer> {
	
	public AppEligibilityInfo findByAppNum(Integer appNum);
	
}
