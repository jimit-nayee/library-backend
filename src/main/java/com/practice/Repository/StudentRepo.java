package com.practice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.practice.Model.Student;


//@Repository
public interface StudentRepo extends MongoRepository<Student, String>{

	Optional<Student> findByEnrollNum(String string);
	List<Student> findByEnrollNumAndPassword(String string, String password);

	Optional<Student> findUserByName(String name);
}
