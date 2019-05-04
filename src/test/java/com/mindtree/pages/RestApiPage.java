package com.mindtree.pages;

import java.io.IOException;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RestApiPage {
	static Request request;
	OkHttpClient client = new OkHttpClient();
	static Response response;
	static Logger log = Logger.getLogger(RestApiPage.class.getName());
	
	/**
	 * To laucnch the github repository
	 * @param keyword
	 */
	public void launchGithub(String keyword) {
		request = new Request.Builder().url("https://api.github.com/search/repositories?q=" + "<" + keyword + ">").get()
				.addHeader("cache-control", "no-cache")
				.addHeader("postman-token", "6c2bf264-4a24-09bc-a6e5-baf3d7d00fb0").build();
		
		log.info("Github repository launch");
		
	}

	/**
	 * To make the request 
	 * @throws IOException
	 */
	public void searchTheRepository() throws IOException {
		response = client.newCall(request).execute();
		System.err.println(response.body().toString());
		log.info("Rest request make");
	}

	/**
	 * To check the response contains the success code
	 * @param code
	 */
	public void checkResponseCodeInOutput(Integer code) {
		Assert.assertTrue(response.code() == code);
		log.info(code+ " code found in the response");
	}
}
