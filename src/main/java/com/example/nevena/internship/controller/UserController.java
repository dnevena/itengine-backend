package com.example.nevena.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nevena.internship.controller.dto.UserDTO;
import com.example.nevena.internship.domain.User;
import com.example.nevena.internship.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("")
	public User createUser(@RequestBody UserDTO userDto ) {
		return userService.createUser(userDto.getName(), userDto.getEmail(), userDto.getUserRoleId());
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable ("id")  Long id) {
		userService.deleteUser(id);
		
	}
	
	@GetMapping("/{id}")
	public User findUser(@PathVariable ("id")  Long id ) {
		return userService.findOne(id);
	}
	
	@PostMapping("/{id}")
	public User editUser( @PathVariable ("id")  Long id,@RequestBody UserDTO userDto) {
		String name = userDto.getName();
		String email = userDto.getEmail();
		return userService.editUser(id,name,email);
	}
	
	@GetMapping("")
	public List<User>findAll() {
		return userService.findAll();
	}
}
