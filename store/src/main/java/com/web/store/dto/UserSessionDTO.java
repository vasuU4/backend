package com.web.store.dto;

import org.springframework.stereotype.Component;

@Component("userSessionDTO")
public class UserSessionDTO extends ResponseDTO {
	private int userId;
	private String username;
	private String fullName;
	private StoreDTO store;

	private String token;

	@Override
	public String toString() {
		return "UserSessionDTO [userId=" + userId + ", username=" + username + ", fullName=" + fullName + ", store="
				+ store + ", token=" + token + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public StoreDTO getStore() {
		return store;
	}

	public void setStore(StoreDTO store) {
		this.store = store;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}

