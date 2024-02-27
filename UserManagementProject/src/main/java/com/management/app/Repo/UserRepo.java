package com.management.app.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);

}
