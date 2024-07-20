package com.hsa.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsa.Enity.CitizenApplication;
import java.util.List;



public interface CitizenRepo extends JpaRepository<CitizenApplication, Integer> {
	public CitizenApplication findByCitizenId(Integer citizenId);
}
