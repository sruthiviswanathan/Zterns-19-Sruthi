package com.zilker.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.zilker.constants.Constants;
import com.zilker.inter.Login;

public class Youtube implements Login {
	
	private static Logger logger = Logger.getLogger(Facebook.class.getName());

	@Override
	public void login(String username, String password) {
		// TODO Auto-generated method stub
		
		if(username.equals(Constants.user) && password.equals(Constants.pass))
		{
			logger.log(Level.INFO,"valid user");
		}
		else
			logger.log(Level.INFO,"invalid login");
	}

}
