package com.example.nevena.internship.domain;

import com.example.nevena.internship.domain.enumeration.Role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String email;
	
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean active;
	
	private String activationLink;
	
	@OneToMany(mappedBy="user")
	private List<Article> lista = new ArrayList<>();
	
	
	public User() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Article> getLista() {
		return lista;
	}
	public void setLista(List<Article> lista) {
		this.lista = lista;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getActivationLink() {
		return activationLink;
	}
	public void setActivationLink(String activationLink) {
		this.activationLink = activationLink;
	}
	
	
}
