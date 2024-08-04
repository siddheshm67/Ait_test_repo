package com.hsa.co.enity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coId;
	
	private String noticeStatus;
	private String noticePrintDate;
	private String noticeS3Url;
	
	private Integer appNum;
	
	public Integer getAppNum() {
		return appNum;
	}
	public void setAppNum(Integer appNum) {
		this.appNum = appNum;
	}
	
	public Integer getCoId() {
		return coId;
	}
	public void setCoId(Integer coId) {
		this.coId = coId;
	}
	public String getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	public String getNoticePrintDate() {
		return noticePrintDate;
	}
	public void setNoticePrintDate(String noticePrintDate) {
		this.noticePrintDate = noticePrintDate;
	}
	public String getNoticeS3Url() {
		return noticeS3Url;
	}
	public void setNoticeS3Url(String noticeS3Url) {
		this.noticeS3Url = noticeS3Url;
	}
	
	
	
}
