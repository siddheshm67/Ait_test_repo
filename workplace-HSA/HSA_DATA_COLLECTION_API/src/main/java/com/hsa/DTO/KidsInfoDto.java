package com.hsa.DTO;

import java.util.List;

import com.hsa.Entity.Kid;

public class KidsInfoDto {
	
	private Integer citizenAppId;
	private List<Kid> kids;
	
	public Integer getCitizenAppId() {
		return citizenAppId;
	}
	public void setCitizenAppId(Integer citizenAppId) {
		this.citizenAppId = citizenAppId;
	}
	public List<Kid> getKids() {
		return kids;
	}
	public void setKids(List<Kid> kids) {
		this.kids = kids;
	}
	
	
}
