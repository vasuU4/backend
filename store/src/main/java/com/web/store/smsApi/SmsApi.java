package com.web.store.smsApi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

@Component("smsApi")
public class SmsApi {
	public String sendSms() {
		try {
			// Construct data
			String apiKey = "apikey=" + "TOiSdOyavUY-h5gqaVIqhC8znR2gHfdddPeH4Pd3Z7";
			String message = "&message=" + "HI vasu";
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "+919618255073";

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}
}



// steps to integrate api

// go to setting and select api keys

//create a new api key

//then go to api integration in the website

//last step is to select full api documentation and copy paste the code and use it!!!!!!!
