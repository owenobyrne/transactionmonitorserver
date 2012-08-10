package com.rxp.transactionmonitorserver.resource;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.rxp.transactionmonitorserver.model.Device;

 
@Component
@Path("/device")
public class DeviceResource {
     
    // The @Context annotation allows us to have certain contextual objects
    // injected into this class.
    // UriInfo object allows us to get URI information (no kidding).
    @Context
    UriInfo uriInfo;
 
    // Another "injected" object. This allows us to use the information that's
    // part of any incoming request.
    // We could, for example, get header information, or the requestor's address.
    @Context
    Request request;
         
    // Use data from the client source to create a new Person object, returned in JSON format.  
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Device postDevice(
            MultivaluedMap<String, String> deviceParams
            ) {
         
        String deviceId = deviceParams.getFirst("deviceId");
         
        System.out.println("Storing posted " + deviceId);
     
        Device device = new Device();
        device.setId(deviceId);
        return device;
                         
    }
    
    @GET
    public String pokeDevice() {
    	
    	
    	Sender sender = new Sender("AIzaSyCj3buI0JttTJPcIy4c272UBSWOdt3bW1A");
    	Message message = new Message.Builder().build();
    	Result result = null;
		try {
			result = sender.send(message, "APA91bH-kTFAlNxgqM8BlTi8qhGe0nh9Y9Y3_Ur7pBjab3vt08zUnSIYcxQ2AHK38LTG9bfmazJ4ElUPXq2_6kXcqg-o-yZZdDoydR8xzdvGWDK4rdvF5EQE9RZhUjz8u4m8P80KfvEF3Q0MfiaPpcKQ1e2pmacGaA", 5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if (result.getMessageId() != null) {
    		 String canonicalRegId = result.getCanonicalRegistrationId();
    		 if (canonicalRegId != null) {
    		   // same device has more than one registration ID: update database
    		 }
    		} else {
    		 String error = result.getErrorCodeName();
    		 if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
    		   // application has been removed from device - unregister database
    		 }
    		}

    	
    	
    	return "poked!";
    }

}