package com.hsa.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsa.Entity.Education;
import com.hsa.Entity.Kid;

public interface KidsRepo extends JpaRepository<Kid, Integer>{

	List<Kid> findByCitizenAppId(Integer citizenAppId);
}
