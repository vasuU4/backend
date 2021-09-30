package com.web.store.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "login_users")
@NamedQuery(name = "LoginUsers.findAll", query = "SELECT a FROM LoginUsers a")
public class LoginUsers extends BaseModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_login_user_id")
	private int pkLoginUserId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "status")
	private int status;
	
	@OneToMany(mappedBy = "loginUser", fetch = FetchType.LAZY)
	private List<StoreUser> storeUsers;

	public List<StoreUser> getStoreUsers() {
		return storeUsers;
	}

	public void setStoreUsers(List<StoreUser> storeUsers) {
		this.storeUsers = storeUsers;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPkLoginUserId() {
		return pkLoginUserId;
	}

	public void setPkLoginUserId(int pkLoginUserId) {
		this.pkLoginUserId = pkLoginUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
