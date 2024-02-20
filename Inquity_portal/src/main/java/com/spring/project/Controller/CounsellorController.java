package com.spring.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.project.Binding.DashboardResponse;
import com.spring.project.entity.Conunsellor;
import com.spring.project.service.CounsellorService;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	@Autowired
	private CounsellorService counsellorService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("conunsellor", new Conunsellor());
		return "loginView";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest httpServletRequest, Model model) {
		httpServletRequest.getSession(true).invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String handleLogin(Conunsellor counsellor,HttpServletRequest httpServletRequest,Model model) {
		Conunsellor conunObj = counsellorService.loginCheck(counsellor.getEmail(), counsellor.getPwd());
		if (conunObj == null) {
			model.addAttribute("errormsg", "invalid login details");
			return "loginView";
		}else {	
			HttpSession httpSession = httpServletRequest.getSession(true);
			httpSession.setAttribute("CID", conunObj.getCid());	
		}
		return "redirect:dashbaord";
	}
	
	@GetMapping("/dashbaord")
	public String buildDashBoard(HttpServletRequest httpServletRequest,Model model) {
		HttpSession session = httpServletRequest.getSession(false);
		Object object = session.getAttribute("CID");
		Integer cidInteger = (Integer)object;
		DashboardResponse dashboardResponse = counsellorService.getDashboardInfo(cidInteger);
		
		model.addAttribute("dashboard", dashboardResponse);
		
		return "dashboardView";
	}
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("conunsellor", new Conunsellor());
		return "registerView";
	}
	
	@PostMapping("/register")
	public String handleRegistraction(Conunsellor conunsellor,Model model) {
		String msg = counsellorService.saveCounseller(conunsellor);
		model.addAttribute("msg", msg);
		return "registerView";
	}
	
	@GetMapping("/forgot-pwd")
	public String recoverPwdPage(Model model) {
		return "forgotPwdView";
	}
	
	@GetMapping("/recover-pwd")
	public String recoverPwd(@RequestParam String email,Model model){
		boolean statuString = counsellorService.recoverPwd(email);
		if (statuString) {
			model.addAttribute("smsg", "password sent to email");
		}else {
			model.addAttribute("errormsg","Invalid email");
		}
		
		return "forgotPwdView";
	}
	
	
	
	
	
	
	
	
	
	

}
