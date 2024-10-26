package com.ssn.api.service;

import org.springframework.stereotype.Service;

@Service
public class DataService {
	
	public String getSsnState(String ssn) {
		
		String firstDigit = ssn.substring(0,1);
		
		String stateName;
	
		switch(firstDigit) {
		  case "1":
			  stateName = "california";
		    break;
		  case "2":
			  stateName = "utah";
		    break;
		  case "3":
			  stateName = "washington"; 
			    break;
		  case "4":
			  stateName = "idaho"; 
			    break;
		  case "5":
			  stateName = "nebraska";
			    break;
		  case "6":
			  stateName = "minnesota";  
			    break;
		  case "7":
			  stateName = "rhode island";
			    break;
		  case "8":
			  stateName = "wisconsin";  
			    break;
		  case "9":
			  stateName = "vermont";
			    break;
			    
		  default:
			 stateName = "INVALID";
		}
		
		return stateName;
	}

}
