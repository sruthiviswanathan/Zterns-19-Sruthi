package com.zilker.onlinejobsearch.Exception;

public class LocationNotFoundException extends ApplicationException {
	private static final long serialVersionUID = 8352409210508508185L;
	private String errorCode="LOCATION_ERR";
	private String errorMessage="LOCATION NOT FOUND";
	
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
