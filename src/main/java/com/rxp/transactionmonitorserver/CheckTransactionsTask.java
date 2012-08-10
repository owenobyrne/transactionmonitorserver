package com.rxp.transactionmonitorserver;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.rxp.transactionmonitorserver.model.Transaction;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Component
public class CheckTransactionsTask {

	protected static Logger logger = Logger.getLogger("service");

	public void checkTransactions() {
		logger.debug("Start schedule");
		System.out.println("Checking for new transactions...");
		/*
		 * List<Transaction> transactions = getTransactionList(); if
		 * (transactions.size() > 2) { // 541821719407 //
		 * AIzaSyCj3buI0JttTJPcIy4c272UBSWOdt3bW1A
		 * System.out.println("More than 2 results!"); Transaction t =
		 * transactions.get(0);
		 */
		Sender sender = new Sender("AIzaSyCj3buI0JttTJPcIy4c272UBSWOdt3bW1A");
		Message message = new Message.Builder().build();
		Result result = null;
		try {
			result = sender
					.send(message,
							"APA91bH-kTFAlNxgqM8BlTi8qhGe0nh9Y9Y3_Ur7pBjab3vt08zUnSIYcxQ2AHK38LTG9bfmazJ4ElUPXq2_6kXcqg-o-yZZdDoydR8xzdvGWDK4rdvF5EQE9RZhUjz8u4m8P80KfvEF3Q0MfiaPpcKQ1e2pmacGaA",
							5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result.getMessageId() != null) {
			String canonicalRegId = result.getCanonicalRegistrationId();
			if (canonicalRegId != null) {
				// same device has more than one registration ID: update
				// database
				System.out.println("duplicate");
			}
		} else {
			String error = result.getErrorCodeName();
			if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
				// application has been removed from device - unregister
				// database
				System.out.println("Not registered");
			}
		}
		/*
		 * 
		 * 
		 * trayIcon.displayMessage("New Transaction!", t.getCardholderName() +
		 * " has paid you " + t.getAmount() + " " + t.getCurrency(),
		 * TrayIcon.MessageType.INFO); }
		 */

	}

	public List<Transaction> getTransactionList() {
		List<Transaction> transactions = null;
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create();
			WebResource webResource = client
					.resource("http://payb.in/v1/transactions");
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			System.out.println("Got a 200 response!");

			transactions = response
					.getEntity(new GenericType<List<Transaction>>() {
					});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return transactions;
	}

}
