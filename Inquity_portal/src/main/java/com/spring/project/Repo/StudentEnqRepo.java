package com.spring.project.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.project.entity.StudentEnq;

public interface StudentEnqRepo extends JpaRepository<StudentEnq, Integer> {
	
	public List<StudentEnq> findByCid(Integer cid);

}
