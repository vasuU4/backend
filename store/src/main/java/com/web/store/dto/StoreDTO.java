package com.web.store.dto;

import com.web.store.model.Stores;

public class StoreDTO extends BaseDTO {
	private int storeId;
	private String name;
	
	@Override
	public String toString() {
		return "StoreDTO [storeId=" + storeId + ", name=" + name + "]";
	}

	public StoreDTO(Stores store) {
		if (store != null) {
			this.storeId = store.getPkStoreId();
			this.name = store.getStoreName();
		}
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
