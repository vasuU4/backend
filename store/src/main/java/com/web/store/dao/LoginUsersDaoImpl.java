package com.web.store.dao;

import org.springframework.stereotype.Component;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;

import com.web.store.model.LoginUsers;
import com.web.store.model.Stores;

@Component("loginUsersDao")
public class LoginUsersDaoImpl extends AbstractGenericDao<LoginUsers> implements LoginUsersDao {

	@Override
	public LoginUsers getLoginUser(String userName, String password) {
		try {
			String queryString = "SELECT loginUser FROM LoginUsers loginUser "
					             + " WHERE loginUser.userName =:userName AND loginUser.password =:password";
			Query query = getSession().createQuery(queryString);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			return (LoginUsers) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public Stores getStore(LoginUsers loginUser) {
		String queryString = "Select store FROM StoreUser storeUser JOIN storeUser.store store WHERE storeUser.loginUser = :loginUser AND storeUser.status = 1 ";
		Query<Stores> query = getSession().createQuery(queryString, Stores.class);
		query.setParameter("loginUser", loginUser);
		return query.getSingleResult();
	}

}
