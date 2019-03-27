package com.zilker.jpa.customException;

public class EmployeeNotFoundException extends ApplicationException {


	private static final long serialVersionUID = 6568758340443155217L;
	private String errorCode="EMP_NOT_FOUND";
	private String errorMessage="EMPLOYEE NOT FOUND";
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
