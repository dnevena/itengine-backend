package com.example.nevena.internship.controller.dto;

public class ResponseMessageDTO {

	private String message;
	
	

	public ResponseMessageDTO() {
		super();
	}


	public ResponseMessageDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
