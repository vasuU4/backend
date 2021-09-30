package com.web.store.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseModel {

	private Integer addedby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date addedon;

	public Integer getAddedby() {
		return addedby;
	}

	public void setAddedby(Integer addedby) {
		this.addedby = addedby;
	}

	public Date getAddedon() {
		return addedon;
	}

	public void setAddedon(Date addedon) {
		this.addedon = addedon;
	}

}
