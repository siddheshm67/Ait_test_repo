package com.management.app.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.Entity.State;

public interface StateRepo extends JpaRepository<State, Integer>{
	
	public List<State> findByCountryID(Integer countryID);
	
}
