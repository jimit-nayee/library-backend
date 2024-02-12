package com.practice.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.Model.Student;
import com.practice.filter.CustomAuthenticationFilter;
import com.practice.manager.CustomAuthenticationManager;
import com.practice.service.StudentService;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StudentControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@InjectMocks
	StudentController studentController;

	@MockBean
	CustomAuthenticationFilter customAuthenticationFilter;

	@MockBean
	CustomAuthenticationManager customAuthenticationManager;

	@MockBean
	StudentService studentService;

	// Junit test for .... operation
	@Test
	public void givenStudentObject_whenCreateStudent_thenReturnSavedStudent()
			throws JsonProcessingException, Exception {

		// given
		Student student = new Student("713", "vishal", "student", "1234567890", "godhra", "IT", "vishal");
		BDDMockito.given(studentService.register(ArgumentMatchers.any(Student.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// when
		ResultActions response = mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(student)));

		// then
		response.andExpect(status().isCreated()).andExpect(jsonPath("$.enrollNum", is(student.getEnrollNum())))
				.andExpect(jsonPath("$.name", is(student.getName())))
				.andExpect(jsonPath("$.role", is(student.getRole())))
				.andExpect(jsonPath("$.number", is(student.getNumber())))
				.andExpect(jsonPath("$.address", is(student.getAddress())))
				.andExpect(jsonPath("$.department", is(student.getDepartment())))
				.andExpect(jsonPath("$.password", is(student.getPassword())));
	}

	// Junit test for login operation
	@Test
	public void givenStudent_whenLogin_thenReturnSuccess() throws JsonProcessingException, Exception {

		// given
		Student student = new Student();
		BDDMockito.given(studentService.login(student)).willAnswer((invocation) -> invocation.getArguments());

		// when
		ResultActions res = mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(student)));

		// then
		res.andDo(print()).andExpect(status().isOk());
	}
}