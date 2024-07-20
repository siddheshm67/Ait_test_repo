package com.hsa.dc.dto;

import com.hsa.dc.entity.Kid;

public class KidsDto {
	
	private Integer appNum;
	
	private java.util.List<Kid> kids;

	public Integer getAppNum() {
		return appNum;
	}

	public void setAppNum(Integer appNum) {
		this.appNum = appNum;
	}

	public java.util.List<Kid> getKids() {
		return kids;
	}

	public void setKids(java.util.List<Kid> kids) {
		this.kids = kids;
	}
	
	

}
