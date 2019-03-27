package com.zilker.crud;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * first launch class redirects the user based on his choice
 * 1.create 2.edit 3.delete 4.display
 */
public class Index {
	private static final Logger logger = Logger.getLogger(Index.class.getName());

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		try {

			Redirect redirect = new Redirect();

			char c;
			do {
				logger.log(Level.INFO,
						"enter your choice \n 1.Create new contact \n 2.Edit a contact \n 3.Delete a contact \n 4.Display contact");
				int ch = sc.nextInt();
				switch (ch) {
				case 1:
					redirect.create();
					break;
				case 2:
					redirect.edit();
					break;
				case 3:
					redirect.delete();
					break;
				case 4:
					redirect.display();
					break;
				default:
					logger.log(Level.INFO, "select a valid choice");
					break;
				}
				logger.log(Level.INFO, "To continue press Y or N to exit");
				c = sc.next().charAt(0);

			} while (c == 'y' || c == 'Y');

			logger.log(Level.INFO, "BYE");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			sc.close();
		}

	}
}
