package com.zilker.basics;

import java.util.*;

public class Contact {

	String name;
	String email;
	String number;

	Contact(String name, String email, String number) {
		this.name = name;
		this.email = email;
		this.number = number;
	}

	/*
	 * the method compares the value of two instances of same class
	 */
	public static void compare(Contact c1, Contact c2) {
		if (c1.name.equals(c2.name) && c1.email.equals(c2.email) && c1.number.equals(c2.number)) {
			System.out.println("same");
		} else
			System.out.println("different");
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String name, email, number;

		name = sc.nextLine();
		email = sc.nextLine();
		number = sc.nextLine();

		Contact c1 = new Contact(name, email, number);

		name = sc.nextLine();
		email = sc.nextLine();
		number = sc.nextLine();

		Contact c2 = new Contact(name, email, number);

		compare(c1, c2);

	}

}
