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
@Table(name = "stores")
@NamedQuery(name = "Stores.findAll", query = "SELECT a FROM Stores a")
public class Stores extends BaseModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_store_id")
	private int pkStoreId;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "status")
	private int status;
	
	@OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
	private List<StoreUser> storeUsers;
	

	public List<StoreUser> getStoreUsers() {
		return storeUsers;
	}

	public void setStoreUsers(List<StoreUser> storeUsers) {
		this.storeUsers = storeUsers;
	}

	public int getPkStoreId() {
		return pkStoreId;
	}

	public void setPkStoreId(int pkStoreId) {
		this.pkStoreId = pkStoreId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
