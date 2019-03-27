package com.zilker.basics;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * program that prints the current date and time with day in format (Current Date: Wed
2015.10.07 at 05:48:19 AM UTC)
 */
public class Datetime {
	private static Logger logger = Logger.getLogger(Datetime.class.getName());

	public static void main(String[] args) {

		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss a");
		LocalDateTime now = LocalDateTime.now();

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("E");

		logger.log(Level.INFO,
				"Current Date: " + format.format(date) + " " + df.format(now) + " at " + tf.format(now) + " UTC ");
	}
}
