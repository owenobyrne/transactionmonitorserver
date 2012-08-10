package com.rxp.transactionmonitorserver.mvc;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rxp.transactionmonitorserver.OAuth4jServiceProvider;

@Controller
@RequestMapping("/connect")
public class ConnectPaybinController {
	private final String CALLBACKURL = "http://10.10.1.2:8080/transactionmonitorserver/paybin/verify";
	
	@Autowired 
	@Qualifier("paybinOAuth4jServiceProvider")
	OAuth4jServiceProvider paybinOAuth4jServiceProvider;
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView connectPaybin(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    System.out.println("In connect...");
		
	    String url = paybinOAuth4jServiceProvider.getOAuth4jService().getAuthenticationURL(CALLBACKURL);
		System.out.println("Got url " + url);
		
		TreeMap<String, Object> model = new TreeMap<String, Object>();
	    
		model.put("authorize_url", url);
		
	    return new ModelAndView("connect_paybin", model);
	  }

	

}
