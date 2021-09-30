package com.web.store.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "store_user")
@NamedQuery(name = "StoreUser.findAll", query = "SELECT a FROM StoreUser a")
public class StoreUser extends BaseModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_store_user_id")
	private int pkStoreUserId;

	@Column(name = "status")
	private int status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_login_user_id")
	private LoginUsers loginUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_store_id")
	private Stores store;
	
	public int getPkStoreUserId() {
		return pkStoreUserId;
	}

	public void setPkStoreUserId(int pkStoreUserId) {
		this.pkStoreUserId = pkStoreUserId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	public LoginUsers getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUsers loginUser) {
		this.loginUser = loginUser;
	}

	public Stores getStore() {
		return store;
	}

	public void setStore(Stores store) {
		this.store = store;
	}






}
