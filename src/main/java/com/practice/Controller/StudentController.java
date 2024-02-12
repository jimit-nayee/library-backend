package com.practice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.practice.Model.Student;
import com.practice.service.StudentService;
import com.practice.serviceImplementation.StudentServiceImpl;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/register")
	public ResponseEntity<Student> register(@RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.register(student),HttpStatus.CREATED);
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> login(@RequestBody Student student) {
		return studentService.login(student);
	}
}
