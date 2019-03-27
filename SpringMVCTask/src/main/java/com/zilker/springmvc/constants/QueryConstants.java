package com.zilker.springmvc.constants;

public class QueryConstants {
	public static final String CONNECTIONSTRING = "jdbc:mysql://localhost:3306/springTask";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root@123";
	public static final String USERINSERT="insert into user(name,pass,phone,email,age) values(?,?,?,?,?)";
}
