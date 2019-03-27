package com.zilker.servlet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.zilker.servlet.constants.DbConstants;


public class DbUtils {

	private static final Logger logger = Logger.getLogger(DbUtils.class.getName());

	/*
	 * establishes connection
	 */
	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DbConstants.connectionString, DbConstants.username, DbConstants.password);
			logger.log(Level.INFO, "connected");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error connecting with SQL Driver");
		}
		return conn;
	}
	/*
	 * closes connection
	 */

	public static void closeConnection(Connection conn, PreparedStatement pst, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error closing the connection variables");
		}
	}

}