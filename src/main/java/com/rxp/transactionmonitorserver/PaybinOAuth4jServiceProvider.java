package com.rxp.transactionmonitorserver;

import org.oauth4j.core.OAuth4JConsumer;
import org.oauth4j.core.OAuth4JService;
import org.oauth4j.util.OAuth4JException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PaybinOAuth4jServiceProvider implements OAuth4jServiceProvider {

	private OAuth4JService oauth4jService;
	
	public PaybinOAuth4jServiceProvider() {
		OAuth4JConsumer consumer = new OAuth4JConsumer("tonr-consumer-key", "SHHHHH!!!!!!!!!!");
		try {
			oauth4jService = new OAuth4JService(consumer, PaybinServiceProvider.class, "Payb.in");
		} catch (OAuth4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public OAuth4JService getOAuth4jService() {
		return oauth4jService;
	}

	public void setOAuth4jService(OAuth4JService oauth4jService) {
		this.oauth4jService = oauth4jService;
	}
	
	
	
	
}
