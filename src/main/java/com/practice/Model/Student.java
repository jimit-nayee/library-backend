package com.practice.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document("Student")
@Component
public class Student {

	@Id
	@Indexed(unique = true)
	private String enrollNum;
	private String name;
	private String role;
	private String number;
	private String address;
	private String department;
	private String password;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEnrollNum() {
		return enrollNum;
	}

	public void setEnrollNum(String enrollNum) {
		this.enrollNum = enrollNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Student [enrollNum=" + enrollNum + ", name=" + name + ", role=" + role + ", number=" + number
				+ ", address=" + address + ", department=" + department + ", password=" + password + "]";
	}

	public Student(String enrollNum, String name, String role, String number, String address, String department,
			String password) {
		super();
		this.enrollNum = enrollNum;
		this.name = name;
		this.role = role;
		this.number = number;
		this.address = address;
		this.department = department;
		this.password = password;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}