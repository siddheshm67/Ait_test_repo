package com.hsa.dc.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsa.dc.entity.Kid;

@Repository
public interface KidsRepo extends JpaRepository<Kid, Integer> {
	
	public List<Kid> findByAppNum(Integer appNum);

}

