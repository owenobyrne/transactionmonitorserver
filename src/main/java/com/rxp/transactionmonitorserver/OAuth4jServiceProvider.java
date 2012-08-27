package com.rxp.transactionmonitorserver;

import org.oauth4j.core.OAuth4JService;
import org.oauth4j.core.OAuth4JToken;
import org.oauth4j.util.OAuth4JException;

public interface OAuth4jServiceProvider {
	public OAuth4JService getOAuth4jService();
	public void setOAuth4jService(OAuth4JService oauth4jService);
	public OAuth4JToken fetchAccessToken(String verifier) throws OAuth4JException;
	public String getAuthenticationURL(String callback) throws OAuth4JException;
	public boolean gotAccessToken();
	
}
