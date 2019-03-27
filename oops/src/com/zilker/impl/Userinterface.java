package com.zilker.impl;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.zilker.bean.User;

import java.util.Scanner;
public class Userinterface {
   private static Logger logger = Logger.getLogger(Userinterface.class.getName());


	public static void main(String args[])
	{
		String name,password;
		Scanner sc = new Scanner(System.in);
		logger.log(Level.INFO,"enter your choice");
		logger.log(Level.INFO,"1.facebook");
		logger.log(Level.INFO,"2.Google");
		logger.log(Level.INFO,"3.youtube");
		User u = new User();
		
		int ch = sc.nextInt();
		name = sc.next();
		password=sc.next();
		
		switch(ch)
		{
		case 1:
			Facebook f = new Facebook();
			f.login(name,password);
			break;
		case 2:
			Google g = new Google();
			g.login(name,password);
			break;
		case 3:
			Youtube y = new Youtube();
			y.login(name,password);
			break;
			
		default : logger.log(Level.INFO,"enter proper choice");
		break;
		}
	
		
	}
	
}
