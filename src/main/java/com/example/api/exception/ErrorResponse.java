package com.example.api.exception;

public class ErrorResponse {
	
	private int code;
	
	private String description;

	public ErrorResponse() {
	}
	
	public ErrorResponse(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}