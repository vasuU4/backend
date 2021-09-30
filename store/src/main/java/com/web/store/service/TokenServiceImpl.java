package com.web.store.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.config.Constants;
import com.web.store.dao.TokenDao;
import com.web.store.exception.UnAuthorizedException;
import com.web.store.helpers.UtilityHelper;
import com.web.store.model.LoginUsers;
import com.web.store.model.Stores;
import com.web.store.model.Token;

@Component("tokenService")
@Transactional
@Service
public class TokenServiceImpl extends BaseServiceImpl implements TokenService {

	@Autowired
	TokenDao tokenDao;

	@Override
	public Token validateToken(String tokenString) throws UnAuthorizedException {
		Token token = null;
		token = tokenDao.checkIsValid(tokenString);
		if (token != null) {
			token.setRecentActive(new Date());
			tokenDao.saveOrUpdate(token, 1);
		} else {
			throw new UnAuthorizedException("Invalid token");
		}
		return token;
	}

	@Override
	public Token generateToken(LoginUsers loginUser, Stores store) {
		Token token = new Token();
		token.setToken(generatedToken());
		token.setStore(store);
		token.setLoginUser(loginUser);
		token.setRecentActive(new Date());
		int userId = loginUser.getPkLoginUserId();

		if (store != null) {
			token.setStore(store);
		}
		tokenDao.saveOrUpdate(token);
		return token;
	}

	private String generatedToken() {
		String timestamp = Long.toString(new Date().getTime());
		return timestamp + UtilityHelper.getRandomString(Constants.TOKEN_LENGTH - timestamp.length());
	}

}
