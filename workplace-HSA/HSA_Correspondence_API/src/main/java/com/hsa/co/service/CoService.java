package com.hsa.co.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.hsa.co.enity.CoEntity;

public interface CoService {
	
	public List<CoEntity> getNoticesByAppNoAndStatus(Integer appNum,String status);
	public boolean printNotices(Integer noticeId) throws FileNotFoundException;
	public CoEntity getNoticeByAppNo(Integer appNo);
	
	
}
