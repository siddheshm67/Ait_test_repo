package com.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Repo.CustRepo;
import com.demo.enitity.Customer;

@RestController
public class CustRestController {
	
	@Autowired
	private CustRepo custRepo;
	
	@Autowired
	private BCryptPasswordEncoder bEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public String registerCustomer(@RequestBody Customer customer) {
		customer.setcPwd(bEncoder.encode(customer.getcPwd()));
		custRepo.save(customer);
		
		return "Registration successful..";
		
	}
	
	@PostMapping("/login")
	public String loginCheck(@RequestBody Customer customer) {
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customer.getcName(), customer.getcPwd());	
		try {
			Authentication authentication = authenticationManager.authenticate(token);
			if (authentication.isAuthenticated()) {
				return "login sccessful";
			}
		} catch (Exception e) {
			//logger
		}
		return "invalid credentials";
		
	}
	
	
	
	
}
