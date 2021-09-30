package com.web.store.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.store.helpers.HttpRequestHelper;

@Component("baseService")
public class BaseServiceImpl implements BaseService{
	
	@Autowired
	HttpRequestHelper httpRequestHelper;
	
	public Map<String, Object> parseRequest(HttpServletRequest httpServletRequest) {
		return httpRequestHelper.parseRequest(httpServletRequest);
	}
}
