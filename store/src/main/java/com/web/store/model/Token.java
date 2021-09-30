package com.web.store.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "token")
@NamedQuery(name = "Token.findAll", query = "SELECT a FROM Token a")
public class Token extends BaseModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_token_id")
	private int pkTokenId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_login_user_id")
	private LoginUsers loginUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_store_id")
	private Stores store;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "recent_active")
	private Date recentActive;
	
	@Column(name = "token")
	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getPkTokenId() {
		return pkTokenId;
	}

	public void setPkTokenId(int pkTokenId) {
		this.pkTokenId = pkTokenId;
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

	public Date getRecentActive() {
		return recentActive;
	}

	public void setRecentActive(Date recentActive) {
		this.recentActive = recentActive;
	}


}
