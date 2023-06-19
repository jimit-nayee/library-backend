package com.practice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.practice.Model.Student;
import com.practice.Repository.StudentRepo;
import com.practice.security.UserSecurity;

@Service
public class UserInfoDetailsService implements UserDetailsService {

	@Autowired
	StudentRepo studentRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		Optional<Student> student = studentRepo.findUserByName(username);
		
		if(student.isPresent()) {
			return new UserSecurity(student.get());
			
		}
		else {
			throw new UsernameNotFoundException(username);
		}
	}
}
