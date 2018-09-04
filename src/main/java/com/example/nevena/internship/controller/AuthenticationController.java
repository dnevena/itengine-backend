package com.example.nevena.internship.controller;

import com.example.nevena.internship.config.security.JWTUtils;
import com.example.nevena.internship.controller.dto.LogInDTO;
import com.example.nevena.internship.controller.dto.ResponseMessageDTO;
import com.example.nevena.internship.domain.User;
import com.example.nevena.internship.service.MailService;
import com.example.nevena.internship.service.UserService;
import com.example.nevena.internship.service.exception.CredentialsInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authtentication")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @PostMapping("/login")
    public ResponseEntity logIn(@RequestBody LogInDTO logInDTO) {
        User user;
        try {
            user = userService.checkUser(logInDTO.getUsername(), logInDTO.getPassword());
        } catch (CredentialsInvalidException e) {
            return new ResponseEntity<>(new ResponseMessageDTO(e.getMessage()), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseMessageDTO(JWTUtils.createToken(user)), HttpStatus.OK);
    }

}
	
