package com.web.store.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface BaseService {
	public Map<String, Object> parseRequest(HttpServletRequest httpServletRequest);
} 
