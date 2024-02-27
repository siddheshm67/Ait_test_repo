package com.management.app.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.management.app.Bindings.LoginForm;
import com.management.app.Bindings.RegisterForm;
import com.management.app.Bindings.ResetPwdForm;
import com.management.app.Entity.User;

@Service
public interface UserService {
	
	public Map<Integer, String> getCountries();
	public Map<Integer, String> getStates(Integer countryID);
	public Map<Integer, String> getCity(Integer stateID);
	public User getUser(String email);
	public boolean saveUser(RegisterForm registerForm);
	public User loginUSer(LoginForm loginForm);
	public User resetPwd(ResetPwdForm resetPwdForm);
	public String generatePwd();
	
}
