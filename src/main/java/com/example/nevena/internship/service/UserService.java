package com.example.nevena.internship.service;

import java.util.ArrayList;
import java.util.List;

import com.example.nevena.internship.domain.enumeration.Role;
import com.example.nevena.internship.service.exception.CredentialsInvalidException;
import com.example.nevena.internship.service.exception.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nevena.internship.domain.User;
import com.example.nevena.internship.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User createUser(String username, String email, String password) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setActive(true);
		user.setRole(Role.USER);
		String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
		
        userRepository.save(user);
		
		return user;
	}
	
	 public User registration(String username, String password, String email) throws UsernameAlreadyExistsException {
		if(userRepository.findOneByUsername(username) != null) {
			throw new UsernameAlreadyExistsException();
		}

		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);
		user.setRole(Role.USER);
		user.setActive(false);
		userRepository.save(user);
		return user;
	}
	
	public void deleteUser(Long id) {
		User user = userRepository.findOneById(id);
		user.setActive(false);
		userRepository.save(user);
	}
	
	
	public User findOne(Long id) {
		User user = userRepository.findOneById(id);
		return user;
	}
	
	public User userActivation(String key){
		User user = userRepository.findOneByActivationLink(key);
		if(user== null){
			return null;
		}
		
		user.setActive(true);
		user.setActivationLink(null);
		userRepository.save(user);
		return user;
	}
	
	
	public User editUser(Long id, String username,String password, String email) {
		User user = userRepository.findOneById(id);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		
		return userRepository.save(user);
		
	}
	
	
	public List<User> findAll() {
		List<User> lista= userRepository.findAll();
		List<User> novaLista = new ArrayList<>();
		
		for (User u : lista) {
			if (u.isActive()) 
				novaLista.add(u);
		}
		
		return novaLista;
	}

	public User checkUser(String username, String password) throws CredentialsInvalidException {
		User user = userRepository.findOneByUsername(username);
		if (user == null ) {
			throw new CredentialsInvalidException();
		}

		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new CredentialsInvalidException();
		}
		
		return user;
	}	
	
	public void activateAccount(String key){
		User user = userRepository.findOneByActivationLink(key);
		user.setActive(true);
		user.setActivationLink(null);
		userRepository.save(user);
	}
	
	
	
}

