package com.zilker.jpa.customException;

public class EmailAlreadyExistsException extends ApplicationException {


	private static final long serialVersionUID = 5972875804720640581L;
	private String errorCode="DUP_EMAIL";
	private String errorMessage="EMAIL ALREADY EXISTS";
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
