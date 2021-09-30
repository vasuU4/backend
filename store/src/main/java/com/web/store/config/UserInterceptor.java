package com.web.store.config;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.web.store.dto.ApiErrorDTO;
import com.web.store.helpers.UtilityHelper;
import com.web.store.model.Token;
import com.web.store.service.TokenService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ComponentScan("com.web.store")
@PropertySource(value = { "classpath:application.properties" })
public class UserInterceptor implements HandlerInterceptor {
	
	private static final Logger LOGGER = Logger.getLogger(UserInterceptor.class);

	@Autowired
	TokenService tokenService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		System.out.println("requested url ---> " + request.getRequestURI());
		
		LOGGER.info("-------------------starting--------------------------");

		List<String> urlList = Arrays.asList(Mappings.LOGIN_USER, Mappings.VALIDATE_USER_TOKEN);
		for (String url : urlList) {
			if (request.getRequestURI().equals("/store" + url)) {
				System.out.println("--->  requested url equals <---");
				return true;
			}
		}

		String tokenString = request.getParameter("token");
		System.out.println("tokenString ----> " + tokenString);

		if (tokenString != null && tokenString.length() != 0) {
			Token token = tokenService.validateToken(tokenString);
			if (token != null) {
				request.setAttribute("store_id", token.getStore().getPkStoreId());
				request.setAttribute("user_id", token.getLoginUser().getPkLoginUserId());
				return true;
			}
		}

		String responseText = UtilityHelper.getJsonFromObject(new ApiErrorDTO(HttpStatus.OK, "Invalid token found"));
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write(responseText);

		return false;

	}

}