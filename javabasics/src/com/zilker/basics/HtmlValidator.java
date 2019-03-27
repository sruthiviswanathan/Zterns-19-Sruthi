package com.zilker.basics;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class HtmlValidator {

	private static Pattern pattern;
	private static Matcher matcher;
	private static final String htmlPattern = "<(.*?)>([^<]+)</\\1>";
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		

		System.out.println("enter the html tag to check");
		String input = scanner.nextLine();
		boolean valid = validate(input);
		if (valid == true)
			System.out.println("Valid tag");
		else
			System.out.println("Invalid tag");
	}

	public static boolean validate(String input) {
		pattern = Pattern.compile(htmlPattern);
		matcher = pattern.matcher(input);
		return matcher.matches();
	}
}

