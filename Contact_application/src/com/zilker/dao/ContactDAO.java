package com.zilker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.zilker.beans.UserContact;
import com.zilker.constants.Constants;
import com.zilker.utils.Dbutils;

public class ContactDAO {
	private static final Logger logger = Logger.getLogger(ContactDAO.class.getName());
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	int contactId = 0;

	/*
	 * method that creates a new contactMain
	 */
	public void createMainContact(UserContact contact) {
		try {
			conn = Dbutils.getConnection();

			pst = conn.prepareStatement(Constants.contactMainInsert);
			pst.setString(1, contact.getFirstName());
			pst.setString(2, contact.getLastName());
			pst.setString(3, contact.getEmail());
			pst.executeUpdate();
			logger.log(Level.INFO, "Contact_Main created !");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "OOPS!! Error creating contact");
			e.printStackTrace();
		} finally {
			Dbutils.closeConnection(conn, pst, rs);
		}
	}

	/*
	 * method that creates a new mobile contact
	 */
	public void createMobileContact(UserContact contact) {
		try {
			conn = Dbutils.getConnection();

			pst = conn.prepareStatement(Constants.emailToContactId);
			pst.setString(1, contact.getEmail());
			rs = pst.executeQuery();
			if (rs.next())
				if (rs.getString(4).equals(contact.getEmail())) {
					String test = rs.getString(3);
					contactId = Integer.parseInt(test);
				}
			pst = conn.prepareStatement(Constants.mobileInsert);
			pst.setInt(1, contactId);
			pst.setInt(2, contact.getMobileExt());
			pst.setLong(3, contact.getMobileNo());
			pst.executeUpdate();
			logger.log(Level.INFO, "Mobile created !");

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "OOPS!! Error creating contact");
			e.printStackTrace();
		} finally {
			Dbutils.closeConnection(conn, pst, rs);
		}
	}

	/*
	 * method that creates a new office contact
	 */
	public void createOfficeContact(UserContact contact) {
		try {
			conn = Dbutils.getConnection();

			pst = conn.prepareStatement(Constants.emailToContactId);
			pst.setString(1, contact.getEmail());
			rs = pst.executeQuery();
			if (rs.next())
				if (rs.getString(4).equals(contact.getEmail())) {
					String test = rs.getString(3);
					contactId = Integer.parseInt(test);
				}
			pst = conn.prepareStatement(Constants.officeInsert);
			pst.setInt(1, contactId);
			pst.setInt(2, contact.getOfficeExt());
			pst.setLong(3, contact.getOfficeNo());
			pst.executeUpdate();

			logger.log(Level.INFO, "office created !");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "OOPS!! Error creating contact");
			e.printStackTrace();
		} finally {
			Dbutils.closeConnection(conn, pst, rs);
		}
	}

	/*
	 * method that creates a new home contact
	 */
	public void createHomeContact(UserContact contact) {
		try {
			conn = Dbutils.getConnection();

			pst = conn.prepareStatement(Constants.emailToContactId);
			pst.setString(1, contact.getEmail());
			rs = pst.executeQuery();
			if (rs.next())
				if (rs.getString(4).equals(contact.getEmail())) {
					String test = rs.getString(3);
					contactId = Integer.parseInt(test);
				}
			pst = conn.prepareStatement(Constants.homeInsert);
			pst.setInt(1, contactId);
			pst.setLong(2, contact.getHomeNo());
			pst.setInt(3, contact.getAreaCode());
			pst.setInt(4, contact.getCountryCode());
			pst.executeUpdate();

			logger.log(Level.INFO, "Home details created !");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "OOPS!! Error creating contact");
			e.printStackTrace();
		} finally {
			Dbutils.closeConnection(conn, pst, rs);
		}
	}

	/*
	 * method that deletes an existing contact by taking mail id as input
	 */

	public void deleteContact(UserContact contact) {

		try {
			conn = Dbutils.getConnection();

			pst = conn.prepareStatement(Constants.emailToContactId);
			pst.setString(1, contact.getEmail());
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				if (rs.getString(4).equals(contact.getEmail())) {
					String test = rs.getString(3);
					contactId = Integer.parseInt(test);
				}
			pst = conn.prepareStatement(Constants.deleteContact);
			pst.setInt(1, contactId);
			pst.executeUpdate();
			logger.log(Level.INFO, "Contact deleted !");

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "OOPS!! Error deleting contact");
			e.printStackTrace();
		} finally {
			Dbutils.closeConnection(conn, pst, rs);
		}

	}
	/*
	 * method that edits an existing contactMain by taking mail id as input
	 */

	public void editMainContact(UserContact contact) {
		try {
			conn = Dbutils.getConnection();

			pst = conn.prepareStatement(Constants.updateContactMain);
			pst.setString(1, contact.getFirstName());
			pst.setString(2, contact.getLastName());
			pst.setString(3, contact.getEmail());
			pst.executeUpdate();
			logger.log(Level.INFO, "ContactMain updated !");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "OOPS!! Error updating contactMain");
			e.printStackTrace();
		} finally {
			Dbutils.closeConnection(conn, pst, rs);
		}
	}

	/*
	 * method that edits an existing mobile contact by taking mail id as input
	 */
	public void editMobileContact(UserContact contact) {
		try {
			conn = Dbutils.getConnection();

			pst = conn.prepareStatement(Constants.emailToContactId);
			pst.setString(1, contact.getEmail());
			ResultSet rs1 = pst.executeQuery();
			if (rs1.next())
				if (rs1.getString(4).equals(contact.getEmail())) {
					String test = rs1.getString(3);
					contactId = Integer.parseInt(test);
				}
			pst = conn.prepareStatement(Constants.updateMobile);
			pst.setInt(1, contact.getMobileExt());
			pst.setLong(2, contact.getMobileNo());
			pst.setInt(3, contactId);
			pst.setLong(4, contact.getOldMobileNo());
			pst.executeUpdate();
			logger.log(Level.INFO, "MobileContact updated !");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "OOPS!! Error updating Mobile contact");
			e.printStackTrace();
		} finally {
			Dbutils.closeConnection(conn, pst, rs);
		}

	}

	/*
	 * method that edits an existing office contact by taking mail id as input
	 */
	public void editOfficeContact(UserContact contact) {
		try {
			conn = Dbutils.getConnection();

			pst = conn.prepareStatement(Constants.emailToContactId);
			pst.setString(1, contact.getEmail());
			ResultSet rs1 = pst.executeQuery();
			if (rs1.next())
				if (rs1.getString(4).equals(contact.getEmail())) {
					String test = rs1.getString(3);
					contactId = Integer.parseInt(test);
				}
			pst = conn.prepareStatement(Constants.updateOffice);
			pst.setInt(1, contact.getOfficeExt());
			pst.setLong(2, contact.getOfficeNo());
			pst.setInt(3, contactId);
			pst.setLong(4, contact.getOldOfficeNo());
			pst.executeUpdate();
			logger.log(Level.INFO, "OfficeContact updated !");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "OOPS!! Error updating Office contact");
			e.printStackTrace();
		} finally {
			Dbutils.closeConnection(conn, pst, rs);
		}
	}

	/*
	 * method that edits an existing home contact by taking mail id as input
	 */
	public void editHomeContact(UserContact contact) {
		try {
			conn = Dbutils.getConnection();

			pst = conn.prepareStatement(Constants.emailToContactId);
			pst.setString(1, contact.getEmail());
			ResultSet rs1 = pst.executeQuery();
			if (rs1.next())
				if (rs1.getString(4).equals(contact.getEmail())) {
					String test = rs1.getString(3);
					contactId = Integer.parseInt(test);
				}
			pst = conn.prepareStatement(Constants.updateHome);
			pst.setLong(1, contact.getHomeNo());
			pst.setInt(2, contact.getAreaCode());
			pst.setInt(3, contact.getCountryCode());
			pst.setInt(4, contactId);
			pst.setLong(5, contact.getOldHomeNo());
			pst.executeUpdate();

			logger.log(Level.INFO, "Home_details updated !");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "OOPS!! Error updating Home details");
			e.printStackTrace();
		} finally {
			Dbutils.closeConnection(conn, pst, rs);
		}
	}

	/*
	 * method that displays an existing contact by taking mail id as input
	 */

	public void displayContact(UserContact contact) {
		try {

			conn = Dbutils.getConnection();

			pst = conn.prepareStatement(Constants.emailToContactId);
			pst.setString(1, contact.getEmail());
			ResultSet rs1 = pst.executeQuery();
			if (rs1.next())
				if (rs1.getString(4).equals(contact.getEmail())) {
					String test = rs1.getString(3);
					contactId = Integer.parseInt(test);
				}

			pst = conn.prepareStatement(Constants.displayMain);
			pst.setInt(1, contactId);
			rs = pst.executeQuery();
			while (rs.next()) {
				logger.log(Level.INFO, "\n first name: " + rs.getString(1) + "\nlast name: " + rs.getString(2)
						+ "\nEmail: " + rs.getString(4));
			}
			pst = conn.prepareStatement(Constants.displayMobile);
			pst.setInt(1, contactId);
			rs = pst.executeQuery();
			while (rs.next()) {
				logger.log(Level.INFO, "\n mobile ext: " + rs.getString(2) + "\n mobile no: " + rs.getString(3));
			}
			pst = conn.prepareStatement(Constants.displayOffice);
			pst.setInt(1, contactId);
			rs = pst.executeQuery();
			while (rs.next()) {
				logger.log(Level.INFO, "\n office ext: " + rs.getString(2) + "\n office no: " + rs.getString(3));
			}
			pst = conn.prepareStatement(Constants.displayHome);
			pst.setInt(1, contactId);
			rs = pst.executeQuery();
			while (rs.next()) {
				logger.log(Level.INFO, "\n home no: " + rs.getString(2) + "\n area code: " + rs.getString(3)
						+ "\n country code: " + rs.getString(4));
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error displaying contact details");
		} finally {
			Dbutils.closeConnection(conn, pst, rs);
		}
	}

}
