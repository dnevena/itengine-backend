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

import com.example.nevena.internship.controller.dto.RoleDTO;
import com.example.nevena.internship.domain.Role;
import com.example.nevena.internship.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping("")
	public Role createRole(@RequestBody RoleDTO roleDto) {
		return roleService.createRole(roleDto.getType());
	}
	
	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable ("id") Long id) {
		roleService.deleteRole(id);
	}

	@GetMapping("/{id}")
	public Role findOne (@PathVariable ("id") Long id) {
		return roleService.findOne(id);
	}
	
	@GetMapping("")
	public List<Role>findAlll() {
		return roleService.findAll();
	}
	
	@PostMapping("/{id}")
	public Role editRole(@PathVariable ("id") Long id,@RequestBody RoleDTO roleDto) {
		return roleService.editRole(id, roleDto.getType());
		
	}

}
