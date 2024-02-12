package com.practice.serviceImplementation;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.Exception.ResourceNotFoundException;
import com.practice.Model.Student;
import com.practice.Repository.StudentRepo;
import com.practice.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private StudentRepo studentRepo;

	public StudentServiceImpl(StudentRepo studentRepo, PasswordEncoder passwordEncoder) {
		this.studentRepo = studentRepo;
	    this.passwordEncoder = passwordEncoder;
	}

	
	@Override
	public Student register(Student student) {
		
		String hashPassword = passwordEncoder.encode(student.getPassword());
		student.setPassword(hashPassword);
		
		Optional<Student> alreadyStudent = studentRepo.findByEnrollNum(student.getEnrollNum());
		
		if(alreadyStudent.isPresent()) {
			throw new ResourceNotFoundException("Student already present with given enrollment number:" +student.getEnrollNum());
		}
		
		return studentRepo.save(student);
	}

	@Override
	public ResponseEntity<String> login(Student student) {
		Optional<Student> students = studentRepo.findByEnrollNum(student.getEnrollNum());

		if (!students.isEmpty()) {
			Student storedStudent = students.get();
			

			if (passwordEncoder.matches(student.getPassword(), storedStudent.getPassword())) {
				return new ResponseEntity<String>(students.get().getRole(), HttpStatus.OK);
			}
		}

		return new ResponseEntity<String>("Student not Found", HttpStatus.NOT_FOUND);
	}

}