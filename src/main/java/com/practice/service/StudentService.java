package com.practice.service;

import org.springframework.http.ResponseEntity;

import com.practice.Model.Student;

public interface StudentService {

	public Student register(Student student);
	public ResponseEntity<String> login(Student student);
}
