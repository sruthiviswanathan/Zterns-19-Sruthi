package com.zilker.springmvc.delegate;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zilker.springmvc.bean.User;
import com.zilker.springmvc.dao.UserDao;

@Service
public class UserDelegate {
	@Autowired
	UserDao userDao;
	
	public void register(User user) {
		
		try {
			userDao.register(user);

		} catch (SQLException e) {
			System.out.println("exception here");
		}
	}
	
	public User validateUser(User user) {
		boolean flag = false;
		try {
			
		user=userDao.validateUser(user);

		} catch (SQLException e) {
			System.out.println("exception here");
		}
		return user;
	}
	
}
