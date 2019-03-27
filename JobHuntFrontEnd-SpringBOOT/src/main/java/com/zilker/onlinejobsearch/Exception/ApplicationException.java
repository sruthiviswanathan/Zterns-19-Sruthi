package com.zilker.onlinejobsearch.Exception;

public class ApplicationException extends Exception {

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 8274170134309561938L;
	private String errorCode;
	private String errorMessage;
	private Object errorData;
	public ApplicationException() {
		
	}
	
	public ApplicationException(String ErrorCode, Object ErrorMessage) {
		this.errorCode="";
		this.errorMessage="";
	}
	
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

	public Object getErrorData() {
		return errorData;
	}

	public void setErrorData(Object errorData) {
		this.errorData = errorData;
	}
	
}
