package com.web.store.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.dao.LoginUsersDao;
import com.web.store.dao.StoresDao;
import com.web.store.dto.ResponseDTO;
import com.web.store.dto.StoreDTO;
import com.web.store.dto.UserLoginDTO;
import com.web.store.dto.UserSessionDTO;
import com.web.store.exception.AccessRestrictedException;
import com.web.store.exception.InvalidCredentialsException;
import com.web.store.exception.UnAuthorizedException;
import com.web.store.model.LoginUsers;
import com.web.store.model.Stores;
import com.web.store.model.Token;
import com.web.store.smsApi.SmsApi;

@Component("loginService")
@Transactional
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
	@Autowired
	LoginUsersDao loginUsersDao;
	
	@Autowired
	SmsApi smsApi;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	StoresDao storesDao;
	
	@Override
	public UserSessionDTO loginUser(UserLoginDTO userLoginDTO)
			throws AccessRestrictedException, InvalidCredentialsException {
		UserSessionDTO userSessionDTO = new UserSessionDTO();
		String username = userLoginDTO.getUsername();
		String password = userLoginDTO.getPassword();
		
		LoginUsers loginUser = new LoginUsers();

		try {
			loginUser = loginUsersDao.getLoginUser(username, password);
			userSessionDTO = getUserSession(loginUser);

		} catch (NoResultException e) {
			throw new InvalidCredentialsException();
		} finally {
		}
		Stores store = null;
		store = storesDao.findById(userSessionDTO.getStore().getStoreId());

		Token token = tokenService.generateToken(loginUser, store);
		userSessionDTO.setToken(token.getToken());
		System.out.println(userSessionDTO.toString());
		return userSessionDTO;
	}
	
	

	@Override
	public UserSessionDTO getUserFromToken(String tokenStr) throws UnAuthorizedException, AccessRestrictedException {
		Token token = tokenService.validateToken(tokenStr);
		return getUserSession(token.getLoginUser());
	}
	
	private UserSessionDTO getUserSession(LoginUsers loginUser) throws AccessRestrictedException {
		UserSessionDTO userSessionDTO = new UserSessionDTO();

		Stores store = null;
		
		System.out.println(userSessionDTO.toString());

		userSessionDTO.setUserId(loginUser.getPkLoginUserId());
		userSessionDTO.setUsername(loginUser.getUserName());
		userSessionDTO.setFullName(loginUser.getFirstName() + " " + loginUser.getLastName());
		
			try {
				store = loginUsersDao.getStore(loginUser);
				userSessionDTO.setStore(new StoreDTO(store));

			} catch (NoResultException e) {
				throw new AccessRestrictedException("Access disabled");
			}
	
		return userSessionDTO;
	}


}
