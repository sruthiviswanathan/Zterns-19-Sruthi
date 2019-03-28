package com.zilker.jpa.customException;

public class SpecialityNotFoundException extends ApplicationException {

	private static final long serialVersionUID = 2956925944825371556L;
	private String errorCode="SPECIALTY_ERR";
	private String errorMessage="SPECIALTY_NOT_FOUND";
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
