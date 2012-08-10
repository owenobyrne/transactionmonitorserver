package com.rxp.transactionmonitorserver;

import org.oauth4j.core.OAuth4JService;

public interface OAuth4jServiceProvider {
	public OAuth4JService getOAuth4jService();
	public void setOAuth4jService(OAuth4JService oauth4jService);
	
}
