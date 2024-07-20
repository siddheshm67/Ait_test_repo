package com.hsa.enitity;

public class ApplicationResponse {

	private String eligabilityStatus;
	private String applicantState;
	
	public ApplicationResponse(String eligabilityStatus, String applicantState) {
		super();
		this.eligabilityStatus = eligabilityStatus;
		this.applicantState = applicantState;
	}
	
	public String getEligabilityStatus() {
		return eligabilityStatus;
	}
	public void setEligabilityStatus(String eligabilityStatus) {
		this.eligabilityStatus = eligabilityStatus;
	}
	public String getApplicantState() {
		return applicantState;
	}
	public void setApplicantState(String applicantState) {
		this.applicantState = applicantState;
	}
	

	
	
	
	
}
