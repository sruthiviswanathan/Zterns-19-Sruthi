package com.zilker.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zilker.springmvc.bean.User;
import com.zilker.springmvc.utils.DButils;

@Repository
public class UserDao {

	 @Autowired
	  DataSource datasource;
	 
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	
	public void register(User user) throws SQLException{
		try {
		
			
			
			 String sql = "insert into user(name,pass,phone,email,age) values(?,?,?,?,?)";
			 jdbcTemplate.update(sql, new Object[] { user.getName(), user.getPassword(), user.getPhoneNumber(),
			   user.getEmail(), user.getAge()});
			 
			/* Connection conn = DButils.getConnection(); 
			PreparedStatement ps = conn.prepareStatement("insert into user(name,pass,phone,email,age) values(?,?,?,?,?)");

			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setLong(3,user.getPhoneNumber());
			ps.setString(4, user.getEmail());
			ps.setInt(5, user.getAge());

			ps.executeUpdate();
			*/
			
			}catch(Exception e) {
				System.out.println("exception");
			
			}
	}
	
	public User validateUser(User user) throws SQLException{
		boolean flag = false;
		try {
			Connection conn = DButils.getConnection();
			PreparedStatement ps = conn.prepareStatement("select email,pass,name from user");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(user.getEmail().equals(rs.getString(1)) &&  user.getPassword().equals(rs.getString(2)) ) {
					user.setName(rs.getString(3));
					flag=true;
					
				}
			}
			
			}catch(SQLException e) {
				throw e;
			}
		return user;
	}
}
