package com.buseni.adminpwa.messages;

public class MessageNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String message;


	public MessageNotFoundException(String message) {
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	

}
