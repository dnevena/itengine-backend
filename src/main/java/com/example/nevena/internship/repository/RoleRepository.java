package com.example.nevena.internship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nevena.internship.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findOneById(Long id);
	List<Role>findAll();
}
