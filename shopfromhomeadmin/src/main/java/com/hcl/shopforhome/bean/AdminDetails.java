package com.hcl.shopforhome.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class AdminDetails {

	@Id
	private String email;
	private String username;
	private String password;

	public AdminDetails() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AdminDetails [email=" + email + ", username=" + username + ", password=" + password + "]";
	}

	public AdminDetails(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
}