package com.demo.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.Repo.CustRepo;
import com.demo.enitity.Customer;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private CustRepo custRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Customer cust = custRepo.findBycName(username);
		
		return new User(cust.getcName(), cust.getcPwd(), Collections.emptyList());

	}

}
