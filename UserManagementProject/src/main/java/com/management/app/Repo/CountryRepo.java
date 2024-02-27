package com.management.app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.Entity.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country, Integer> {

}
