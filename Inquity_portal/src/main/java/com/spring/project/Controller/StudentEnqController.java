package com.spring.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.project.Binding.SearchCriteria;
import com.spring.project.entity.StudentEnq;
import com.spring.project.service.EnquieryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentEnqController {
	
	@Autowired
	private EnquieryService enquieryService;
	
	@GetMapping("/enquiery")
	public String enqpage(Model model) {
		model.addAttribute("enq",new StudentEnq());
		return "addEnqView";
		
	}
	
	@PostMapping("/enquiry")
	public String addEnquiery(@ModelAttribute("enq")StudentEnq enq,HttpServletRequest httpServletRequest,Model model) {
		
		HttpSession session = httpServletRequest.getSession(false);
		Integer cidInteger = (Integer) session.getAttribute("CID");
		enq.setCid(cidInteger);
		
		boolean enqStatus = enquieryService.addEnq(enq);
		
		if(cidInteger == null) {
			return "redirect:/";
		}
		
		if (enqStatus) {
			model.addAttribute("smsg","enqiry added successfully");
		}else {
			model.addAttribute("emsg","failed to add enqiery");
		}
		
		return "addEnqView";
	}
	
	@GetMapping("/enquries")
	public String viewEnquries(HttpServletRequest httpServletRequest,Model model) {
		HttpSession session = httpServletRequest.getSession(false);
		Integer cidInteger = (Integer) session.getAttribute("CID");
		
		
		model.addAttribute("searchCriteria",new SearchCriteria());
		List<StudentEnq> studentEnqList = enquieryService.getEnquires(cidInteger,new SearchCriteria() );
		model.addAttribute("enquries", studentEnqList);
		return "displayEnqView";
		
	}
	
	@PostMapping("/filter-enquries")
	public String filterEnquries(SearchCriteria searchCriteria,HttpServletRequest httpServletRequest,Model model) {
		HttpSession session = httpServletRequest.getSession(false);
		Integer cidInteger = (Integer) session.getAttribute("CID");
		
		/*
		 * if(cidInteger == null) { return "redirect:/"; }
		 */
		
		List<StudentEnq> studentEnqList = enquieryService.getEnquires(cidInteger, searchCriteria);
		model.addAttribute("enquries", studentEnqList);
		return "displayEnqView";
	}
	
	

}
