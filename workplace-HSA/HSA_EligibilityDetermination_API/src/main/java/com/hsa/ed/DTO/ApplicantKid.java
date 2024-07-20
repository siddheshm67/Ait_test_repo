package com.hsa.ed.DTO;

import java.time.LocalDate;

public class ApplicantKid {
	
	private String kidName;
	private LocalDate KidDob;
	private long kidSSn;
	
	public String getKidName() {
		return kidName;
	}
	public void setKidName(String kidName) {
		this.kidName = kidName;
	}
	public LocalDate getKidDob() {
		return KidDob;
	}
	public void setKidDob(LocalDate kidDob) {
		KidDob = kidDob;
	}
	public long getKidSSn() {
		return kidSSn;
	}
	public void setKidSSn(long kidSSn) {
		this.kidSSn = kidSSn;
	}
	
	

}
