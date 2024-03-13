package com.demo.Repo;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.Student;

public interface StudentRepo extends CrudRepository<Student, Integer>{

}
