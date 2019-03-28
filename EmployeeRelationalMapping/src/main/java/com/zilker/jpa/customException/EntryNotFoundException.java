package com.zilker.jpa.customException;

public class EntryNotFoundException extends ApplicationException {

	
	private static final long serialVersionUID = 7190552877483325176L;
	private String errorCode="EMPLOYEE_SPECIALITY_ERR";
	private String errorMessage="EMPLOYEE_SPECIALTY_ENTRY_NOT_FOUND";
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
