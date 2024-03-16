package com.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.enitity.Customer;
import java.util.List;


public interface CustRepo extends JpaRepository<Customer, Integer> {
	
	public Customer findBycName(String cName);

}
