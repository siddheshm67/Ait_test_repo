package com.management.app.service;

import java.util.Map;

import com.management.app.Bindings.LoginForm;
import com.management.app.Bindings.RegisterForm;
import com.management.app.Bindings.ResetPwdForm;
import com.management.app.Entity.User;

public interface UserService {
	
	public Map<Integer, String> getCountries();
	public Map<Integer, String> getStates();
	public Map<Integer, String> getCity();
	public User getUser(String email);
	public boolean saveUser(RegisterForm registerForm);
	public User loginUSer(LoginForm loginForm);
	public User resetPwd(ResetPwdForm resetPwdForm);
	public String generatePwd();
	
}
