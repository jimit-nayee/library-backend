package com.practice.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import com.practice.Model.Student;

@DataMongoTest
public class StudentRepoTests {

	@Autowired
	private StudentRepo studentRepo;
	
	private Student student;
	
	@BeforeEach
	public void setup() {
		student = new Student("709", "jimit", "admin", "7043382913", "jivraj", "IT", "jimit");
	}
	
	
	// Junit test for save student operation
	@Test
	public void givenStudentObject_whenSave_thenReturnSavedStudent() {
		
		//given - precondition
		
		//when - action that we are going to test
		Student savedStudent = studentRepo.save(student);
		
		//then - verify the output
		assertThat(savedStudent).isNotNull();
		assertThat(Integer.parseInt(savedStudent.getEnrollNum())).isGreaterThan(0);
	}
	
	
	// Junit test for get all saved student operation
	@Test
	public void givenStudent_whenFindAll_thenStudentList() {
		
		//given
		
		//when
		List<Student> studentList = studentRepo.findAll();
		
		//then
		assertThat(studentList).isNotNull();
		assertThat(studentList.size()).isGreaterThanOrEqualTo(0);
	}
	
	
	// Junit test for get saved student by enrollment number(id) operation
	@Test
	public void givenStudentObject_whenFindByEnrollNum_thenReturnStudentObject() {
		
		//given
		
		//when
		Student studentDB = studentRepo.findByEnrollNum(student.getEnrollNum()).get();
		
		//then
		assertThat(studentDB).isNotNull();

	}
	
	//Junit test for find student by name operation
	@Test
	public void givenStudentObject_whenFindByName_thenReturnStudentObject() {

		//given

		//when
		Student studentName = studentRepo.findUserByName(student.getName()).get();

		//then
		assertThat(studentName).isNotNull();
	}
	
	//Junit test for update the student address operation
	@Test
	public void givenStudentObject_whenUpdateAddress_thenReturnStudentObject() {

		//given

		//when
		Student std = studentRepo.findByEnrollNum(student.getEnrollNum()).get();
		std.setAddress("ahmedabad");
		Student updatedStudent = studentRepo.save(std);
	
		//then
		assertThat(updatedStudent.getAddress()).isEqualTo("ahmedabad");
	}
}
