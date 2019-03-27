package com.zilker.servlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.servlet.bean.Student;
import com.zilker.servlet.utils.DbUtils;

public class StudentDao {

	public int insertStudentDetails(Student student) throws SQLException {
		
		int i=0;
		try {
		
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into REGISTERUSER values(?,?,?,?)");

		ps.setString(1, student.getName());
		ps.setString(2, student.getPassword());
		ps.setString(3, student.getEmail());
		ps.setString(4, student.getCountry());

		i = ps.executeUpdate();

		
		}catch(Exception e) {
			
		}
		return i;
	}
	
	public ArrayList<Student>  DisplayStudentDetails(Student student) throws SQLException {
		
		ArrayList<Student> stud = new ArrayList<Student>();
	
		try {
		
		Connection conn = DbUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from REGISTERUSER");
		ResultSet resultSet = ps.executeQuery();
		
		while(resultSet.next())
		{
			Student s = new Student();
			s.setName(resultSet.getString(1));
			s.setPassword(resultSet.getString(2));
			s.setEmail(resultSet.getString(3));
			s.setCountry(resultSet.getString(4));
			stud.add(s);
			

		}
		}catch(Exception e) {
			
		}
	
		return stud;
	}
	
}
