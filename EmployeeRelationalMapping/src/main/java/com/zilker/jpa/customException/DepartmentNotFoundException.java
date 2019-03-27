package com.zilker.jpa.customException;

public class DepartmentNotFoundException extends ApplicationException {

	private static final long serialVersionUID = 6726229257950419234L;
	private String errorCode="DEPT_ERR";
	private String errorMessage="DEPARTMENT NOT FOUND";
	
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
