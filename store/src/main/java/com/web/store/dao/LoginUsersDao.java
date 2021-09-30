package com.web.store.dao;

import com.web.store.model.LoginUsers;
import com.web.store.model.Stores;

public interface LoginUsersDao extends GenericDao<LoginUsers>{

	LoginUsers getLoginUser(String userName, String password);

	Stores getStore(LoginUsers loginUser);

}
