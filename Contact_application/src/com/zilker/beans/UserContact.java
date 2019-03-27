package com.zilker.beans;

/*
 * POJO class containing all contact details
 */
public class UserContact {

	private int contactId;
	private String firstName, lastName, email;
	private int mobileExt, officeExt, areaCode, countryCode;
	private long mobileNo, officeNo, homeNo;
	private long oldMobileNo, oldOfficeNo, oldHomeNo;

	public long getOldMobileNo() {
		return oldMobileNo;
	}

	public void setOldMobileNo(long oldMobileNo) {
		this.oldMobileNo = oldMobileNo;
	}

	public long getOldOfficeNo() {
		return oldOfficeNo;
	}

	public void setOldOfficeNo(long oldOfficeNo) {
		this.oldOfficeNo = oldOfficeNo;
	}

	public long getOldHomeNo() {
		return oldHomeNo;
	}

	public void setOldHomeNo(long oldHomeNo) {
		this.oldHomeNo = oldHomeNo;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMobileExt() {
		return mobileExt;
	}

	public void setMobileExt(int mobileExt) {
		this.mobileExt = mobileExt;
	}

	public int getOfficeExt() {
		return officeExt;
	}

	public void setOfficeExt(int officeExt) {
		this.officeExt = officeExt;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(long officeNo) {
		this.officeNo = officeNo;
	}

	public long getHomeNo() {
		return homeNo;
	}

	public void setHomeNo(long homeNo) {
		this.homeNo = homeNo;
	}

}
