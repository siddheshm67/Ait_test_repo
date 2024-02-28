package com.management.app.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.Entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {
	
	public List<City> findByStateID(Integer stateID);

}
