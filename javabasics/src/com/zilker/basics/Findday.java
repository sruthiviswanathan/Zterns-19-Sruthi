package com.zilker.basics;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

/*
 * program that gets dateofbirth and displays the day of the week 
 */
public class Findday {
	private static Logger logger = Logger.getLogger(Findday.class.getName());

	/*
	 * function to identify day of the week given date,month,year
	 */
	static int dayofweek(int d, int m, int y) {
		try {
			int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
			y -= (m < 3) ? 1 : 0;
			return (y + y / 4 - y / 100 + y / 400 + t[m - 1] + d) % 7;
		} catch (Exception e) {
			return -1;
		}
	}

	public static void main(String arg[]) {

		Scanner sc = new Scanner(System.in);
		logger.log(Level.INFO, "enter date");
		int date = sc.nextInt();
		logger.log(Level.INFO, "enter month");
		int month = sc.nextInt();
		logger.log(Level.INFO, "enter year");
		int year = sc.nextInt();

		int day = dayofweek(date, month, year);
		char c = (char) day;
		switch (c) {
		case 1:
			logger.log(Level.INFO, "monday");
			break;
		case 2:
			logger.log(Level.INFO, "tuesday");
			break;
		case 3:
			logger.log(Level.INFO, "wednesday");
			break;
		case 4:
			logger.log(Level.INFO, "thursday");
			break;
		case 5:
			logger.log(Level.INFO, "friday");
			break;
		case 6:
			logger.log(Level.INFO, "saturday");
			break;
		case 7:
			logger.log(Level.INFO, "sunday");
			break;
		default:
			logger.log(Level.INFO, "invalid date");
			break;
		}

	}
}
