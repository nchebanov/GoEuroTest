package com.goeuro.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by nickc on 6/15/16.
 */
public class HttpHelper {

	private static final String API_ENDPOINT = "http://api.goeuro.com/api/v2/position/suggest/en/";
	private static final Integer CONN_TIMEOUT = 1000 * 1;
	private static final Integer READ_TIMEOUT = 1000 * 5;

	public static String queryCities(String cityname) throws IOException {

		URL url;
		URLConnection connection;
		InputStreamReader inputStreamReader;
		BufferedReader bufferedReader;
		String inputLine;
		StringBuffer result = new StringBuffer();

		url = new URL(API_ENDPOINT + cityname);
		connection = url.openConnection();
		connection.setConnectTimeout(CONN_TIMEOUT);
		connection.setReadTimeout(READ_TIMEOUT);
		inputStreamReader = new InputStreamReader(connection.getInputStream());

		bufferedReader = new BufferedReader(inputStreamReader);
		while ((inputLine = bufferedReader.readLine()) != null) {
			result.append(inputLine);
		}

		return result.toString();
	}
}
