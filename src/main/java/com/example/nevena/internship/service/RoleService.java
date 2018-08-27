package com.example.nevena.internship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nevena.internship.domain.Role;
import com.example.nevena.internship.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role createRole(String type) {
		Role role = new Role();
		role.setType(type);
		
		roleRepository.save(role);
		
		return role;
	}
	
	public void deleteRole(Long id) {
		Role role = roleRepository.findOneById(id);
		roleRepository.delete(role);
		
	}

	public Role findOne(Long id) {
		return roleRepository.findOneById(id);
	}

	public Role editRole(Long id, String type) {
		Role role = roleRepository.findOneById(id);
		role.setType(type);
		
		roleRepository.save(role);
		
		return role;
	}
	
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

}

