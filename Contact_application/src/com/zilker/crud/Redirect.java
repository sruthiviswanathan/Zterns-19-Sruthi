package com.zilker.crud;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import com.zilker.beans.UserContact;
import com.zilker.constants.Constants;
import com.zilker.dao.ContactDAO;

public class Redirect {
	private static final Logger logger = Logger.getLogger(Redirect.class.getName());
	Scanner sc = new Scanner(System.in);
	private static Pattern pattern;
	private static Matcher matcher;

	/*
	 * method to get all the required values from the user and calls the
	 * function that creates a new contact
	 */
	void create() {
		try {
			UserContact contact = new UserContact();
			ContactDAO contactdao = new ContactDAO();
			int counter = 0;
			String email = "";
			int mobileExt = 0, officeExt = 0, areaCode = 0, countryCode = 0;
			long mobileNumber = 0, officeNumber = 0, homeNumber = 0;

			logger.log(Level.INFO, "enter FirstName");
			String fname = sc.next();
			logger.log(Level.INFO, "enter LastName");
			String lname = sc.next();
			// email validation
			do {
				logger.log(Level.INFO, "enter emailId (eg: username@domain.com)");
				String emailId = sc.next();
				pattern = Pattern.compile(Constants.emailPattern);
				matcher = pattern.matcher(emailId);
				if (matcher.matches() == true) {
					email = String.valueOf(emailId);
					counter = 1;
					break;
				} else {
					logger.log(Level.INFO, "enter valid email ID");
				}
			} while (counter != 1);

			contact.setFirstName(fname);
			contact.setLastName(lname);
			contact.setEmail(email);
			contactdao.createMainContact(contact);

			counter = 0;
			logger.log(Level.INFO, "Do you want to add mobile number?(If yes press y else n)");
			char choice = sc.next().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				logger.log(Level.INFO, "enter how many mobile numbers you want to save?");
				int numbers = sc.nextInt();
				for (int i = 0; i < numbers; i++) {
					do {
						logger.log(Level.INFO,
								"enter mobileExtension(eg: +91[should start with +,can have 1 to 4 characters] )");
						String mobileex = sc.next();
						pattern = Pattern.compile(Constants.mobileExtPattern);
						matcher = pattern.matcher(mobileex);
						if (matcher.matches() == true) {
							mobileExt = Integer.parseInt(mobileex);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid mobile extension");
						}
					} while (counter != 1);
					counter = 0;
					do {
						logger.log(Level.INFO, "enter mobile number(eg: [9876543210 (10 digits only)])");
						String mobileno = sc.next();
						pattern = Pattern.compile(Constants.mobileNumberPattern);
						matcher = pattern.matcher(mobileno);
						if (matcher.matches() == true) {
							mobileNumber = Long.parseLong(mobileno);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid mobile number");
						}
					} while (counter != 1);
					counter = 0;
					contact.setMobileExt(mobileExt);
					contact.setMobileNo(mobileNumber);
					contact.setEmail(email);
					contactdao.createMobileContact(contact);
				}

			}
			counter = 0;
			logger.log(Level.INFO, "Do you want to add office number?(If yes press y else n)");
			choice = sc.next().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				logger.log(Level.INFO, "enter how many office numbers you want to save?");
				int numbers = sc.nextInt();
				for (int i = 0; i < numbers; i++) {

					do {
						logger.log(Level.INFO, "enter Office Extension(eg: 5678 (3 to 5 characters long))");
						String officeex = sc.next();
						pattern = Pattern.compile(Constants.officeExtPattern);
						matcher = pattern.matcher(officeex);
						if (matcher.matches() == true) {
							officeExt = Integer.parseInt(officeex);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid office extension");
						}
					} while (counter != 1);
					counter = 0;
					do {
						logger.log(Level.INFO, "enter office number(eg:44444444 (6 to 8 characters long))");
						String officeno = sc.next();
						pattern = Pattern.compile(Constants.officeNumberPattern);
						matcher = pattern.matcher(officeno);
						if (matcher.matches() == true) {
							officeNumber = Long.parseLong(officeno);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid office number");
						}
					} while (counter != 1);
					counter = 0;
					contact.setOfficeExt(officeExt);
					contact.setOfficeNo(officeNumber);
					contact.setEmail(email);
					contactdao.createOfficeContact(contact);
				}
			}
			counter = 0;
			logger.log(Level.INFO, "Do you want to add home details?(If yes press y else n)");
			choice = sc.next().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				logger.log(Level.INFO, "enter how many home details you want to save?");
				int numbers = sc.nextInt();
				for (int i = 0; i < numbers; i++) {

					do {
						logger.log(Level.INFO, "enter home number(eg: 88888888 (8 to 10 characters long))");
						String homeno = sc.next();
						pattern = Pattern.compile(Constants.homeNumberPattern);
						matcher = pattern.matcher(homeno);
						if (matcher.matches() == true) {
							homeNumber = Long.parseLong(homeno);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid home number");
						}
					} while (counter != 1);
					counter = 0;
					do {
						logger.log(Level.INFO, "enter area code(eg:234 (2 to 3 characters long))");
						String areacode = sc.next();
						pattern = Pattern.compile(Constants.areaCodePattern);
						matcher = pattern.matcher(areacode);
						if (matcher.matches() == true) {
							areaCode = Integer.parseInt(areacode);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid area code");
						}
					} while (counter != 1);
					counter = 0;
					do {
						logger.log(Level.INFO,
								"enter country code(eg: +234 (1 to 3 characters long, should contain +))");
						String countrycode = sc.next();
						pattern = Pattern.compile(Constants.countryCodePattern);
						matcher = pattern.matcher(countrycode);
						if (matcher.matches() == true) {
							countryCode = Integer.parseInt(countrycode);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid country code");
						}
					} while (counter != 1);
					counter = 0;
					contact.setHomeNo(homeNumber);
					contact.setAreaCode(areaCode);
					contact.setCountryCode(countryCode);
					contactdao.createHomeContact(contact);
				}

			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());

		} finally {

		}

	}

	/*
	 * method to get email from the user and calls the function that deletes an
	 * existing contact
	 */

	void delete() {
		try {
			logger.log(Level.INFO, "enter email");
			String email = sc.next();
			UserContact contact = new UserContact();
			contact.setEmail(email);
			ContactDAO contactdao = new ContactDAO();
			contactdao.deleteContact(contact);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {

		}
	}

	/*
	 * method to get contactID from the user and calls the function that
	 * displays that contact details completely
	 */

	void display() {
		try {
			logger.log(Level.INFO, "enter email");
			String email = sc.next();
			UserContact contact = new UserContact();
			contact.setEmail(email);
			ContactDAO contactdao = new ContactDAO();
			contactdao.displayContact(contact);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {

		}
	}

	/*
	 * method that edits an existing contact
	 */
	void edit() {

		try {
			logger.log(Level.INFO, "enter email of the contact to be updated");
			String email = sc.next();

			UserContact contact = new UserContact();
			ContactDAO contactdao = new ContactDAO();
			int counter = 0;
			int mobileExt = 0, officeExt = 0, areaCode = 0, countryCode = 0;
			long mobileNumber = 0, officeNumber = 0, homeNumber = 0;
			//
			logger.log(Level.INFO, "enter FirstName");
			String fname = sc.next();
			logger.log(Level.INFO, "enter LastName");
			String lname = sc.next();
			contact.setFirstName(fname);
			contact.setLastName(lname);
			contact.setEmail(email);
			contactdao.editMainContact(contact);
			counter = 0;

			logger.log(Level.INFO, "Do you want to edit your mobile number?(If yes press y else n)");
			char choice = sc.next().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				logger.log(Level.INFO, "enter how many mobile numbers you want to edit?");
				int numbers = sc.nextInt();
				for (int i = 0; i < numbers; i++) {
					logger.log(Level.INFO, "enter the mobile number you want to edit");
					long oldMobileNo = sc.nextLong();
					do {
						logger.log(Level.INFO,
								"enter mobileExtension(eg: +91[should start with +,can have 1 to 4 characters] )");
						String mobileex = sc.next();
						pattern = Pattern.compile(Constants.mobileExtPattern);
						matcher = pattern.matcher(mobileex);
						if (matcher.matches() == true) {
							mobileExt = Integer.parseInt(mobileex);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid mobile extension");
						}
					} while (counter != 1);
					counter = 0;
					do {
						logger.log(Level.INFO, "enter mobile number(eg: [9876543210 (10 digits only)])");
						String mobileno = sc.next();
						pattern = Pattern.compile(Constants.mobileNumberPattern);
						matcher = pattern.matcher(mobileno);
						if (matcher.matches() == true) {
							mobileNumber = Long.parseLong(mobileno);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid mobile number");
						}
					} while (counter != 1);
					counter = 0;
					contact.setMobileExt(mobileExt);
					contact.setMobileNo(mobileNumber);
					contact.setEmail(email);
					contact.setOldMobileNo(oldMobileNo);
					contactdao.editMobileContact(contact);
				}

			}
			counter = 0;
			logger.log(Level.INFO, "Do you want to edit office number?(If yes press y else n)");
			choice = sc.next().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				logger.log(Level.INFO, "enter how many office numbers you want to edit?");
				int numbers = sc.nextInt();
				for (int i = 0; i < numbers; i++) {
					logger.log(Level.INFO, "enter the office number you want to edit");
					long oldOfficeNo = sc.nextLong();
					do {
						logger.log(Level.INFO, "enter Office Extension(eg: 5678 (3 to 5 characters long))");
						String officeex = sc.next();
						pattern = Pattern.compile(Constants.officeExtPattern);
						matcher = pattern.matcher(officeex);
						if (matcher.matches() == true) {
							officeExt = Integer.parseInt(officeex);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid office extension");
						}
					} while (counter != 1);
					counter = 0;
					do {
						logger.log(Level.INFO, "enter office number(eg:44444444 (6 to 8 characters long))");
						String officeno = sc.next();
						pattern = Pattern.compile(Constants.officeNumberPattern);
						matcher = pattern.matcher(officeno);
						if (matcher.matches() == true) {
							officeNumber = Long.parseLong(officeno);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid office number");
						}
					} while (counter != 1);
					counter = 0;
					contact.setOfficeExt(officeExt);
					contact.setOfficeNo(officeNumber);
					contact.setEmail(email);
					contact.setOldOfficeNo(oldOfficeNo);
					contactdao.editOfficeContact(contact);
				}
			}
			counter = 0;
			logger.log(Level.INFO, "Do you want to edit home details?(If yes press y else n)");
			choice = sc.next().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				logger.log(Level.INFO, "enter how many home details you want to edit?");
				int numbers = sc.nextInt();
				for (int i = 0; i < numbers; i++) {
					logger.log(Level.INFO, "enter the home number you want to edit");
					long oldHomeNo = sc.nextLong();
					do {
						logger.log(Level.INFO, "enter home number(eg: 88888888 (8 to 10 characters long))");
						String homeno = sc.next();
						pattern = Pattern.compile(Constants.homeNumberPattern);
						matcher = pattern.matcher(homeno);
						if (matcher.matches() == true) {
							homeNumber = Long.parseLong(homeno);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid home number");
						}
					} while (counter != 1);
					counter = 0;
					do {
						logger.log(Level.INFO, "enter area code(eg:234 (2 to 3 characters long))");
						String areacode = sc.next();
						pattern = Pattern.compile(Constants.areaCodePattern);
						matcher = pattern.matcher(areacode);
						if (matcher.matches() == true) {
							areaCode = Integer.parseInt(areacode);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid area code");
						}
					} while (counter != 1);
					counter = 0;
					do {
						logger.log(Level.INFO,
								"enter country code(eg: +234 (1 to 3 characters long, should contain +))");
						String countrycode = sc.next();
						pattern = Pattern.compile(Constants.countryCodePattern);
						matcher = pattern.matcher(countrycode);
						if (matcher.matches() == true) {
							countryCode = Integer.parseInt(countrycode);
							counter = 1;
							break;
						} else {
							logger.log(Level.INFO, "enter valid country code");
						}
					} while (counter != 1);
					counter = 0;
					contact.setHomeNo(homeNumber);
					contact.setAreaCode(areaCode);
					contact.setCountryCode(countryCode);
					contact.setOldHomeNo(oldHomeNo);
					contactdao.editHomeContact(contact);
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());

		} finally {

		}

	}
}
