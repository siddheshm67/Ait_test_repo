package com.management.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.app.service.DashboardService;


@Controller
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/dasboard")
	public String buildDashboard(Model model) {
		String quote = dashboardService.getQuotes();
		//updated some features
		model.addAttribute("quote",quote);
		return "dashboard";
	}
	
	public String getNewQuote() {
		
		return null;
	}
}
