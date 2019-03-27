package com.zilker.basics;

import java.util.Scanner;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.text.*;

/*
 * Program that displays the given amount in different currency formats
 */
public class Currency {

	private static Logger logger = Logger.getLogger(Currency.class.getName());

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double amount = sc.nextDouble();
		sc.close();

		NumberFormat payment;

		payment = NumberFormat.getCurrencyInstance(Locale.US);
		logger.log(Level.INFO, "US: " + payment.format(amount));

		payment = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		logger.log(Level.INFO, "INDIA: " + payment.format(amount));

		payment = NumberFormat.getCurrencyInstance(Locale.GERMAN);
		logger.log(Level.INFO, "GERMAN: " + payment.format(amount));

		payment = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		logger.log(Level.INFO, "FRANCE: " + payment.format(amount));

		payment = NumberFormat.getCurrencyInstance(Locale.ITALY);
		logger.log(Level.INFO, "ITALY: " + payment.format(amount));
	}
}
