package com.rxp.transactionmonitorserver.mvc;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oauth4j.core.OAuth4JRequest;
import org.oauth4j.core.OAuth4JToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rxp.transactionmonitorserver.OAuth4jServiceProvider;
import com.rxp.transactionmonitorserver.PaybinServiceProviderService;
import com.sun.jersey.api.client.ClientResponse;

@Controller
@RequestMapping("/verify")
public class VerifyPaybinController {
	@Autowired 
	@Qualifier("paybinOAuth4jServiceProvider")
	OAuth4jServiceProvider paybinOAuth4jServiceProvider;
	
	@RequestMapping(method = RequestMethod.GET)
	protected void connectPaybin(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    System.out.println("In verify...");
		
	    String token = request.getParameter("oauth_token");
	    String verifier = request.getParameter("oauth_verifier");
	    
	    if (token == null || verifier == null) {
	      throw new IllegalArgumentException("An oauth token and verifier must be provided.");
	    }
	    	
		OAuth4JToken access_token = paybinOAuth4jServiceProvider.fetchAccessToken(verifier);
		System.out.println("Got token " + access_token.toString());

		if (access_token != null) {
			response.sendRedirect("home");
		}
		// TODO what about the case if it is null?
	}

	

}


