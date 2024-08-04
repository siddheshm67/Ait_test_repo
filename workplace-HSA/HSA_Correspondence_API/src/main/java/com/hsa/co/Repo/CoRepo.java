package com.hsa.co.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hsa.co.enity.CoEntity;

@Repository
public interface CoRepo extends JpaRepository<CoEntity, Integer>{
	//public List<CoEntity> findByAppNumAndNoticeStatus(Integer appNum, String noticeStatus);
	
	@Query("from CoEntity where appNum=:appNum and noticeStatus=:status")
	public List<CoEntity> getNotices(Integer appNum,String status);
	
	public CoEntity findByAppNum(Integer appNum);
}
