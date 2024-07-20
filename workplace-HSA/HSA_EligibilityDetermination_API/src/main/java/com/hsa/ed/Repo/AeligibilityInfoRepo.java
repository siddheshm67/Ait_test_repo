package com.hsa.ed.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsa.ed.entity.AppEligibilityInfo;

@Repository
public interface AeligibilityInfoRepo extends JpaRepository<AppEligibilityInfo, Integer> {
	
	public List<AppEligibilityInfo> findByAppNum(Integer appNum);
	
}
