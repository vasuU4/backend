package com.web.store.dao;

import com.web.store.model.Token;

public interface TokenDao extends GenericDao<Token>{

	Token checkIsValid(String tokenString);

}
