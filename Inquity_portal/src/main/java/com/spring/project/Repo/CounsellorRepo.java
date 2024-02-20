package com.spring.project.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.entity.Conunsellor;

public interface CounsellorRepo extends JpaRepository<Conunsellor, Integer> {
	public Conunsellor findByEmailAndPwd(String email,String pwd);
	public Conunsellor findByEmail(String email);

}
