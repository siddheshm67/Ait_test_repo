package com.management.app.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.app.Bindings.LoginForm;
import com.management.app.Bindings.RegisterForm;
import com.management.app.Bindings.ResetPwdForm;
import com.management.app.Entity.User;
import com.management.app.Repo.CountryRepo;
import com.management.app.props.AppProps;
import com.management.app.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AppProps appProps;
	

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("loginform", new LoginForm());
		return "index";
	}

	@PostMapping("/login")
	public String loginCheck(@ModelAttribute("loginform") LoginForm loginForm, Model model) {
		User user = userService.loginUSer(loginForm);
		if (user == null) {
			model.addAttribute("errmsg", appProps.getMessages().get("invalidLogin"));
			return "index";
		}

		if (user.getPwdUpdated().equals("NO")) {
			ResetPwdForm resetPwdForm = new ResetPwdForm();
			resetPwdForm.setUserID(user.getUserID());
			model.addAttribute("resetPwd", resetPwdForm);
			return "resetPwd";
		}

		return "redirect:dasboard";
	}

	@PostMapping("/updatePwd")
	public String resetPwd(@ModelAttribute("resetPwd") ResetPwdForm resetPwdForm, Model model) {

		if (!resetPwdForm.getPwd().equals(resetPwdForm.getConfirmPwd())) {
			model.addAttribute("errmsg", appProps.getMessages().get("invalidPwd"));
			return "resetPwd";
		}

		boolean resetPwdStatus = userService.resetPwd(resetPwdForm);
		if (resetPwdStatus) {
			return "redirect:dasboard";
		}

		model.addAttribute("errmsg", appProps.getMessages().get("pwdUpdateFaild"));
		return "resetPwd";

	}

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/states") public Map<Integer, String>
	 * loadStates(@RequestParam("countryID") Integer cid) {
	 * 
	 * return userService.getStates(cid); }
	 * 
	 * 
	 * @ResponseBody
	 * 
	 * @GetMapping("/cities") public Map<Integer, String>
	 * loadCities(@RequestParam("stateID") Integer sid) {
	 * 
	 * return userService.getStates(sid); }
	 */


	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		Map<Integer,String> countries = userService.getCountries();
		model.addAttribute("countries",countries);
		Map<Integer,String> states = userService.getStates();
		model.addAttribute("states",states);
		Map<Integer,String> cities = userService.getCities();
		model.addAttribute("cities",cities);
		///new lines of code
		//here also
		//added
		return "register";
	}

	@PostMapping("/registeruser")
	public String userRegister(@ModelAttribute("registerForm") RegisterForm registerForm, Model model) {

		
		boolean user = userService.saveUser(registerForm);
		if (user) {
			model.addAttribute("sucmsg", appProps.getMessages().get("invalidLogin"));
		}else {
			model.addAttribute("errmsg", appProps.getMessages().get("regFailuer"));
		}
		
		model.addAttribute("registerForm", new RegisterForm());
		Map<Integer,String> countries = userService.getCountries();
		model.addAttribute("countries",countries);
		Map<Integer,String> states = userService.getStates();
		model.addAttribute("states",states);
		Map<Integer,String> cities = userService.getCities();
		model.addAttribute("cities",cities);
		
		
		return "register";
	}

	public String updatePwd(ResetPwdForm resetPwdForm, Model model) {

		return null;
	}

	public String logout(Model model) {

		return null;
	}

}
