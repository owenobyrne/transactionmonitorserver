package com.rxp.transactionmonitorserver.mvc;

import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oauth4j.core.OAuth4JRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rxp.transactionmonitorserver.OAuth4jServiceProvider;
import com.rxp.transactionmonitorserver.PaybinServiceProviderService;
import com.rxp.transactionmonitorserver.model.Transaction;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired 
	@Qualifier("paybinOAuth4jServiceProvider")
	OAuth4jServiceProvider paybinOAuth4jServiceProvider;
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView viewHome(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    System.out.println("In home...");
		
	    if (!paybinOAuth4jServiceProvider.gotAccessToken()) {
	    	System.out.println("Don't have an access token yet - redirecting to OAuth dance...");
	    	response.sendRedirect("connect");
	    	
	    } else {
	    	OAuth4JRequest transactionsRequest = paybinOAuth4jServiceProvider.getOAuth4jService().createRequest(PaybinServiceProviderService.TRANSACTIONS, null);
			ClientResponse res = paybinOAuth4jServiceProvider.getOAuth4jService().process(transactionsRequest);

			List<Transaction> transactions = res.getEntity(new GenericType<List<Transaction>>() {});

			TreeMap<String, Object> model = new TreeMap<String, Object>();
			model.put("transactions", transactions);
			
		    return new ModelAndView("home", model);
	
	    }
	    // Shouldn't get here/
		return null;
	}

	

}
