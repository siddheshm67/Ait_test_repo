package com.hsa.AppRegister.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsa.AppRegister.Entity.CitizenApp;


public interface AppRepo extends JpaRepository<CitizenApp, Integer>{
	
	
}
