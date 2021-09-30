package com.web.store.helpers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("httpRequestHelper")
public class HttpRequestHelper {
	public Map<String, Object> parseRequest(HttpServletRequest httpServletRequest) {
		String payload = httpServletRequest.getParameter("payload");
		Map<String, Object> request = new HashMap<String, Object>();
		try {
			request = getMapFromString(payload);
			request.put("curUserId", httpServletRequest.getAttribute("user_id"));
			request.put("curStoreId", httpServletRequest.getAttribute("store_id"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return request;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapFromString(String rawString)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		if (rawString != null) {
			result = objectMapper.readValue(rawString, HashMap.class);
		}
		return result;
	}
}
