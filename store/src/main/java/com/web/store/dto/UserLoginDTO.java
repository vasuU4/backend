package com.web.store.dto;
import org.springframework.stereotype.Component;

import com.web.store.config.Constants;

@Component("userLoginDTO")
public class UserLoginDTO extends BaseDTO {

	@Override
	public String toString() {
		return "UserLoginDTO [username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + "]";
	}
	
	private String username;
	private String password;
	private String firstname;
	private String lastname;
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}

