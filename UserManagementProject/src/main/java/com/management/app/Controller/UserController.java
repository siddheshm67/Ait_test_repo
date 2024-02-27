package com.management.app.Controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.management.app.Bindings.LoginForm;
import com.management.app.Bindings.RegisterForm;
import com.management.app.Bindings.ResetPwdForm;

@Controller
public class UserController {
	
	public String index(Model model) {
		
		return null;
	}
	
	public String login(LoginForm loginForm,Model model) {
		
		return null;
	}
	
	public String register(Model model) {
		
		return null;
	}
	
	public String resetPwd(Model model) {
		
		return null;
	}
	
	
	public Map<Integer, String> loadStates(Integer cid){
		
		return null;
	}
	
	
	public Map<Integer, String> loadCities(Integer sid){
		
		return null;
	}
	
	
	public String userRegister(RegisterForm registerForm,Model model) {
		
		return null;
	}
	
	public String updatePwd(ResetPwdForm resetPwdForm,Model model) {
		
		return null;
	}
	
	public String logout(Model model) {
	
		return null;
	}
	
	
	

}
