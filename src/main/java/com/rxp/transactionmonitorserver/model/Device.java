package com.rxp.transactionmonitorserver.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Device {
	private String id;
	
	public Device() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
