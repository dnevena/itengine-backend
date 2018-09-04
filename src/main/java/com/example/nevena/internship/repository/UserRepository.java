package com.example.nevena.internship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nevena.internship.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findOneById(Long id);
	User findOneByActivationLink(String key);
	List<User>findAll();
	User findOneByUsername(String username);
	
}
