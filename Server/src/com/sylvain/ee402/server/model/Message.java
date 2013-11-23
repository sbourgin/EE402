package com.sylvain.ee402.server.model;

public class Message {

	private String message;
	
	public Message(String message) {

		this.message = message; 
		
	}
	
	public Message() {
	
		this.message = "";
		
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
