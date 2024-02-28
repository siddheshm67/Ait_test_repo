package com.management.app.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.management.app.Bindings.LoginForm;
import com.management.app.Bindings.RegisterForm;
import com.management.app.Bindings.ResetPwdForm;
import com.management.app.Entity.User;
import com.management.app.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("loginform",new LoginForm());
		return "index";
	}
	
	@PostMapping("/login")
	public String loginCheck(@ModelAttribute("loginform") LoginForm loginForm,Model model) {
		User user = userService.loginUSer(loginForm);
		if (user == null) {
			model.addAttribute("errmsg","invalid credentials");
			return "index";
		}
		
		if (user.getPwdUpdated().equals("NO")) {
			ResetPwdForm resetPwdForm = new ResetPwdForm();
			resetPwdForm.setUserID(user.getUserID());
			model.addAttribute("resetPwd",resetPwdForm);
			return "resetPwd";
		}
		
		return "redirect:dasboard";
	}
	
	public String register(Model model) {
		
		return null;
	}
	
	@PostMapping("/updatePwd")
	public String resetPwd(ResetPwdForm resetPwdForm, Model model) {
		boolean resetPwdStatus = userService.resetPwd(resetPwdForm);
		if (resetPwdStatus) {
			return "redirect:dashbaord";
		}
		model.addAttribute("errmsg","password update faild");
		return "resetPwd";
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
