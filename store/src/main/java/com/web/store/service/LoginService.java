package com.web.store.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.MultiValueMap;

import com.web.store.dto.ResponseDTO;
import com.web.store.dto.UserLoginDTO;
import com.web.store.dto.UserSessionDTO;
import com.web.store.exception.AccessRestrictedException;
import com.web.store.exception.InvalidCredentialsException;
import com.web.store.exception.UnAuthorizedException;

public interface LoginService {

	public UserSessionDTO getUserFromToken(String tokenStr)
			throws UnAuthorizedException, AccessRestrictedException;

	public UserSessionDTO loginUser(UserLoginDTO userLoginDTO)
			throws AccessRestrictedException, InvalidCredentialsException;

}
