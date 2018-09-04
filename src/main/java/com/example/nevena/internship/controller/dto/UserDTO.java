package com.example.nevena.internship.controller.dto;

import java.util.List;

public class UserDTO {

	private String username;
	
	private String email;
	
	private String password;
	
	private List<Long> userRoleId; 
	
	public UserDTO() {
		super();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Long> getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(List<Long> userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
