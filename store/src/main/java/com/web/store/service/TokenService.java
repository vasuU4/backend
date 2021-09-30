package com.web.store.service;

import com.web.store.exception.UnAuthorizedException;
import com.web.store.model.LoginUsers;
import com.web.store.model.Stores;
import com.web.store.model.Token;

public interface TokenService {

	Token validateToken(String tokenString) throws UnAuthorizedException;

	Token generateToken(LoginUsers loginUser, Stores store);

}
