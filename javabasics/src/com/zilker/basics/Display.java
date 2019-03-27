package com.zilker.basics;

import java.util.*;
/*
 * program that concatenates all the string that user enters with a newline after each string
 */
public class Display {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String str = "";
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			String s1 = sc.nextLine();
			str = str.concat(s1).concat("\n");
		}
		System.out.println(str);
		sc.close();

	}

}
