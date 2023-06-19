package com.practice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.practice.Model.Student;
import com.practice.serviceImplementation.BookServiceImpl;
import com.practice.serviceImplementation.StudentServiceImpl;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {

	@Autowired
	private StudentServiceImpl stdService;

	@Autowired
	private Student student;

	@PostMapping("/register")
	public Student register(@RequestBody Student student) {
		return stdService.register(student);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Student student) {
		return stdService.login(student);
	}
}
