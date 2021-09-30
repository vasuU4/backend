package com.web.store.helpers;

import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilityHelper {

	public static String getRandomString(int length) {

		String time = Long.toString(new Date().getTime());
		String randName = time;
		Random rand = new Random();
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		for (int i = 0; i < length - time.length(); i++) {
			randName += alpha.charAt(rand.nextInt(alpha.length() - 1));
		}
		return randName;
	}

	public static String getJsonFromObject(Object unitChargesMap) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(unitChargesMap);
	}

}
