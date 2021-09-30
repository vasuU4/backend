package com.web.store.dao;

import java.util.Date;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.store.config.Constants;
import com.web.store.model.Token;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

@Component("tokenDao")
public class TokenDaoImpl extends AbstractGenericDao<Token> implements TokenDao {
	
	@Override
	public Token checkIsValid(String tokenString) {
		int expiretime = Constants.TOKEN_EXPIRY_TIME;
		Date now = new Date();
		String queryString = "SELECT t From Token t "
				  +"Where t.token= :token AND TIME_TO_SEC(timediff(now(),t.recentActive)) < :expiretime";
		Query query = getSession().createQuery(queryString);
		query.setParameter("token", tokenString);
		query.setParameter("expiretime", expiretime);
		Token token = null;
		try {
			token = (Token) query.getSingleResult();
		} catch (NoResultException e) {
			token = null;
		}
		return token;
	}

}
