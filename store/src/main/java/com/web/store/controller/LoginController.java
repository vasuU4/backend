package com.web.store.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.web.store.config.Mappings;
import com.web.store.dto.ApiErrorDTO;
import com.web.store.dto.ResponseDTO;
import com.web.store.dto.UserLoginDTO;
import com.web.store.exception.AccessRestrictedException;
import com.web.store.exception.UnAuthorizedException;
import com.web.store.helpers.HttpRequestHelper;
import com.web.store.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@Autowired
	HttpRequestHelper httpRequestHelper;

	@RequestMapping(value = Mappings.LOGIN_USER, method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> LoginUser(HttpServletRequest httpServletRequest) {
		Map<String, Object> request = httpRequestHelper.parseRequest(httpServletRequest);

		UserLoginDTO userLoginDTO = new UserLoginDTO();
		userLoginDTO.setUsername((String) request.get("username"));
		userLoginDTO.setPassword((String) request.get("password"));
		ResponseEntity<ResponseDTO> responseEntity;
		responseEntity = new ResponseEntity<ResponseDTO>(loginService.loginUser(userLoginDTO), HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping(value = Mappings.VALIDATE_USER_TOKEN, method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> ValidateUserToken(HttpServletRequest httpServletRequest) {
		
		Map<String, Object> request = httpRequestHelper.parseRequest(httpServletRequest);
		String tokenStr = (String) request.get("token");
		ResponseEntity<ResponseDTO> responseEntity;
		try {
			responseEntity = new ResponseEntity<ResponseDTO>(loginService.getUserFromToken(tokenStr),HttpStatus.OK);
		} catch (UnAuthorizedException e) {
			responseEntity = new ResponseEntity<ResponseDTO>(new ApiErrorDTO(HttpStatus.UNAUTHORIZED, e.getMessage()),
					HttpStatus.UNAUTHORIZED);
		} catch (AccessRestrictedException e) {
			responseEntity = new ResponseEntity<ResponseDTO>(new ApiErrorDTO(HttpStatus.UNAUTHORIZED, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

}
