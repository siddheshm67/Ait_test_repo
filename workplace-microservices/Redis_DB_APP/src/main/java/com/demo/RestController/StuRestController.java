package com.demo.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Repo.StudentRepo;
import com.demo.entity.Student;

@RestController
public class StuRestController {

	@Autowired
	private StudentRepo studentRepo;
	
	@GetMapping("/students")
	public Iterable<Student> allStudents() {
		return studentRepo.findAll();
		
	}
	
	@PostMapping("/student")
	public String addStudent(@RequestBody Student student) {
		studentRepo.save(student);
		return "student saved";
	}
	
	
}
