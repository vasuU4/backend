package com.web.store.dao;

import org.springframework.stereotype.Component;

import com.web.store.model.Stores;

@Component("storesDao")
public class StoresDaoImpl extends AbstractGenericDao<Stores> implements StoresDao{

}
