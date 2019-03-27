package com.zilker.basics;

import java.util.Scanner;
import java.util.regex.Pattern;

import java.util.regex.Matcher;

public class Password {

	private static final String passwordPattern = "^(?=.*[0-9])(?=[a-zA-Z]{2,})([@$!A-Za-z0-9_-]+)";

	public static void main(String args[]) {
		int count = 0;
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		if (s.length() >= 8 && s.length() <= 15) {
			boolean output = validate(s);
			if (output == true) {
				System.out.println("valid");

				String r1 = "(.*?\\d){3,}";
				String r2 = "([@$!a-zA-Z0-9_-]+)";
				String r3 = "(.*a-z){1,}";
				String r4 = "(.*A-Z){1,}";

				Pattern p1 = Pattern.compile(r1);
				Matcher m1 = p1.matcher(s);
				if (m1.matches() == true) {
					count = count + 1;
				}
				Pattern p2 = Pattern.compile(r2);
				Matcher m2 = p2.matcher(s);
				if (m2.matches()) {
					count = count + 1;
				}
				boolean c = checkString(s);
				if (c == true)
					count = count + 1;

			} else {
				System.out.println("invalid");
			}
		} else

		{
			System.out.println("invalid");
		}
		if (count == 1) {
			System.out.println("weak");
		} else if (count == 2) {
			System.out.println("moderate");
		} else if (count == 3) {
			System.out.println("strong");
		}

		sc.close();
	}

	private static boolean validate(String s) {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile(passwordPattern);
		Matcher m = p.matcher(s);
		return m.matches();
	}

	private static boolean checkString(String s) {
		char ch;
		boolean capitalFlag = false;
		boolean lowerCaseFlag = false;
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			if (Character.isUpperCase(ch)) {
				capitalFlag = true;
			} else if (Character.isLowerCase(ch)) {
				lowerCaseFlag = true;
			}
			if (capitalFlag && lowerCaseFlag)
				return true;
		}
		return false;
	}

}
