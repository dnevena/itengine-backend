package com.example.nevena.internship.controller;

import java.util.List;

import javax.mail.MessagingException;

import com.example.nevena.internship.service.exception.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nevena.internship.controller.dto.RegistrationDTO;
import com.example.nevena.internship.controller.dto.ResponseDTO;
import com.example.nevena.internship.controller.dto.ResponseMessageDTO;
import com.example.nevena.internship.controller.dto.UserDTO;
import com.example.nevena.internship.domain.User;
import com.example.nevena.internship.service.MailService;
import com.example.nevena.internship.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;
	
	
	@PostMapping("")
	public User createUser(@RequestBody UserDTO userDto ) {
		return userService.createUser(userDto.getUsername(), userDto.getEmail(),userDto.getPassword());
	}
	
	@PostMapping("/signUp")
	public ResponseEntity signUp(@RequestBody RegistrationDTO registerDTO) throws MessagingException {
		try {
			userService.registration(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getEmail());
		} catch (UsernameAlreadyExistsException e) {
			return new ResponseEntity<>(new ResponseMessageDTO(e.getMessage()), HttpStatus.CONFLICT);
		}
		//	mailService.sendActivationLinkMail(user);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
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
		String username = userDto.getUsername();
		String email = userDto.getEmail();
		String password = userDto.getPassword();
		return userService.editUser(id,username,password,email);
	}
	
	@GetMapping
	public List<User>findAll() {
		return userService.findAll();
	}
	
	@GetMapping("/userActivation")
	public ResponseDTO userActivation(@RequestParam("key") String key){
		User user = userService.userActivation(key);
		if( user == null){
			return new ResponseDTO ("Korisnik sa ovim linkom ne postoji");
		}
		return new ResponseDTO ("Uspesno");
	}
	
	@GetMapping("/accountActivation")
	public void activateAccount(@RequestParam(value = "key") String key){
		userService.activateAccount(key);
	}
}
