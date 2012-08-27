package com.rxp.transactionmonitorserver;

import org.oauth4j.core.OAuth4JConsumer;
import org.oauth4j.core.OAuth4JService;
import org.oauth4j.core.OAuth4JToken;
import org.oauth4j.util.OAuth4JException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PaybinOAuth4jServiceProvider implements OAuth4jServiceProvider {

	private OAuth4JService oauth4jService;
	private boolean haveAccessToken = false;

	public PaybinOAuth4jServiceProvider() {
		OAuth4JConsumer consumer = new OAuth4JConsumer("tonr-consumer-key", "SHHHHH!!!!!!!!!!");
		try {
			oauth4jService = new OAuth4JService(consumer, PaybinServiceProvider.class, "Payb.in");
		} catch (OAuth4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean gotAccessToken() {
		return this.haveAccessToken;
	}

	public String getAuthenticationURL(String callback) throws OAuth4JException {
		return oauth4jService.getAuthenticationURL(callback);
	}
	
	public OAuth4JToken fetchAccessToken(String verifier) throws OAuth4JException {
		oauth4jService.setVerifier(verifier);
		OAuth4JToken access_token = oauth4jService.fetchAccessToken();
		if (access_token != null) {
			haveAccessToken = true;
		}
		return access_token;
	}
	
	public OAuth4JService getOAuth4jService() {
		return oauth4jService;
	}

	public void setOAuth4jService(OAuth4JService oauth4jService) {
		this.oauth4jService = oauth4jService;
	}
	
	
	
	
}
