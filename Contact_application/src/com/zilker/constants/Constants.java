package com.zilker.constants;

public class Constants {

	public static final String connectionString = "jdbc:mysql://localhost:3306/contactapplication";
	public static final String username = "root";
	public static final String password = "root@123";
	public static final String contactMainInsert = "insert into contact_main1(fname,lname,email) values(?,?,?)";
	public static final String mobileInsert = "INSERT INTO mobile VALUES(?,?,?)";
	public static final String homeInsert = "INSERT INTO home_details VALUES(?,?,?,?)";
	public static final String officeInsert = "INSERT INTO office VALUES(?,?,?)";
	public static final String deleteContact = "delete from contact_main1 where contact_id = ?";
	public static final String displayMain = "SELECT * FROM contact_main1 WHERE contact_id= ?";
	public static final String displayMobile = "SELECT * FROM mobile WHERE contact_id= ?";
	public static final String displayOffice = "SELECT * FROM office WHERE contact_id= ?";
	public static final String displayHome = "SELECT * FROM home_details WHERE contact_id= ?";
	public static final String updateContactMain = "UPDATE contact_main1 SET fname=?,lname=? WHERE email =?";
	public static final String updateMobile = "UPDATE mobile SET mobile_ext=?,mobile_no=? WHERE contact_id =? and mobile_no=?";
	public static final String updateOffice = "UPDATE office SET office_ext=?,office_no=? WHERE contact_id =? and office_no=?";
	public static final String updateHome = "UPDATE home_details SET home_no=?,area_code=?,country_code=? WHERE contact_id =? and home_no=?";
	public static final String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
	public static final String mobileExtPattern = "^\\+[1-9]{1}[0-9]{1,3}$";
	public static final String mobileNumberPattern = "^[789]\\d{9}$";
	public static final String homeNumberPattern = "[1-9]{8,10}";
	public static final String officeExtPattern = "[1-9]{3,5}";
	public static final String officeNumberPattern = "[0-9]{6,8}";
	public static final String areaCodePattern = "[1-9]{2,3}";
	public static final String countryCodePattern = "^\\+[1-9]{1,3}";
	public static final String emailToContactId = "select * from contact_main1 where email = ?";

}
