package com.example.nevena.internship.config.security;

import com.example.nevena.internship.domain.User;
import com.example.nevena.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final String lowercaseUsername = username.toLowerCase();
        final Optional<User> optionalUser = Optional.ofNullable(userRepository.findOneByUsername(lowercaseUsername));

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }

        final User user = optionalUser.get();
        final List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(lowercaseUsername, null, grantedAuthorities);
    }

}
