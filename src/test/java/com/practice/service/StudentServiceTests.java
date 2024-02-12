package com.practice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.practice.Exception.ResourceNotFoundException;
import com.practice.Model.Student;
import com.practice.Repository.StudentRepo;
import com.practice.serviceImplementation.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {

	@Mock
	private StudentRepo studentRepo;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private StudentServiceImpl studentServiceImpl;

	private Student student;

	@BeforeEach
	public void setup() {
		student = new Student("709", "jimit", "admin", "7043382913", "jivraj", "IT", "jimit");
	}

	// Junit test for register operation
	@Test
	public void givenStudentObject_whenSaveStudent_thenReturnStudentObject() {

		// given
		BDDMockito.given(studentRepo.findByEnrollNum(student.getEnrollNum())).willReturn(Optional.empty());
		BDDMockito.given(studentRepo.save(student)).willReturn(student);

		// when
		Student savedStudent = studentServiceImpl.register(student);

		// then
		assertThat(savedStudent).isNotNull();
	}

	// Junit test for check student is present or not operation
	@Test
	public void givenEnrollNum_whenSaveStudent_thenThrowsCustomException() {

		// given
		BDDMockito.given(studentRepo.findByEnrollNum(student.getEnrollNum())).willReturn(Optional.of(student));

		// when
		org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			studentServiceImpl.register(student);
		});

		// then
		verify(studentRepo, never()).save(any(Student.class));
	}

	// Junit test for login operation
	@Test
	public void givenStudentObject_whenLogin_thenThrowNotFound() {

		// given
		BDDMockito.given(studentRepo.findByEnrollNum(student.getEnrollNum())).willReturn(Optional.of(student));

		// when
		ResponseEntity<String> loggedIn = studentServiceImpl.login(student);

		// then
		assertThat(loggedIn).isNotNull();
	}

	// Junit test for login operation
	@Test
	public void givenStudentObject_whenLoginEmpty_thenReturnStudent() {

		// given
		BDDMockito.given(studentRepo.findByEnrollNum(student.getEnrollNum())).willReturn(Optional.empty());

		// when
		ResponseEntity<String> loggedIn = studentServiceImpl.login(student);

		// then
		assertThat(loggedIn).isNotNull();
	}

	// Junit test for login operation
	@Test
	public void givenStudentObject_whenLoginSuccess_thenReturnStudent() {

		// given
		BDDMockito.given(studentRepo.findByEnrollNum(anyString())).willReturn(Optional.of(student));
		BDDMockito.given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);

		// when
		ResponseEntity<String> loggedIn = studentServiceImpl.login(student);

		// then
		assertThat(loggedIn).isEqualTo(new ResponseEntity<String>(student.getRole(), HttpStatus.OK));
	}

//    -------------------------------------------------------------------------
//    below code is given by chatGPT with the help of OP and it is also working
//    -------------------------------------------------------------------------
//    @BeforeEach
//    void setUp() {
//    	passwordEncoder = mock(PasswordEncoder.class);
//        student = new Student();
//        student.setEnrollNum("123456");
//        student.setPassword("password");
//    }
//
//    @Test
//    void registerStudentWhenNotPresent() {
//        when(studentRepo.findByEnrollNum(student.getEnrollNum())).thenReturn(Optional.empty());
//        when(studentRepo.save(student)).thenReturn(student);
//
//        Student savedStudent = studentServiceImpl.register(student);
//
//        assertEquals("123456", savedStudent.getEnrollNum());
//        verify(studentRepo, times(1)).save(student);
//    }
//
//    @Test
//    void registerStudentWhenAlreadyPresent() {
//        when(studentRepo.findByEnrollNum(student.getEnrollNum())).thenReturn(Optional.of(student));
//
//        assertThrows(ResourceNotFoundException.class, () -> studentServiceImpl.register(student));
//        verify(studentRepo, times(0)).save(student);
//    }

}
