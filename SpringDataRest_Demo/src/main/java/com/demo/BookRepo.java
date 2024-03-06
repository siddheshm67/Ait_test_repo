package com.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "books")
public interface BookRepo extends JpaRepository<BookEntity, Integer> {
	
	public List<BookEntity> findByName(@Param("name") String name);
	//books/search/findByName?name=sql

}
