package com.example.nevena.internship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nevena.internship.domain.Role;
import com.example.nevena.internship.domain.User;
import com.example.nevena.internship.domain.UserRole;
import com.example.nevena.internship.repository.RoleRepository;
import com.example.nevena.internship.repository.UserRepository;
import com.example.nevena.internship.repository.UserRoleRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public User createUser(String name, String email, List<Long> userRoleId) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		
		userRepository.save(user);
		
		for (Long l: userRoleId) {
			Role role = roleRepository.findOneById(l);
			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(role);
			
			userRoleRepository.save(userRole);
		}
		
		return user;
	}
	
	
	public void deleteUser(Long id) {
		User user = userRepository.findOneById(id);
		userRepository.delete(user);
	}
	
	
	public User findOne(Long id) {
		User user = userRepository.findOneById(id);
		return user;
	}
	
	public User editUser(Long id, String name, String email) {
		User user = userRepository.findOneById(id);
		user.setName(name);
		user.setEmail(email);
		
		return userRepository.save(user);
		
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
