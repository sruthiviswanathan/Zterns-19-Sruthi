package com.zilker.springboot.delegate;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zilker.springboot.beans.Employee;
import com.zilker.springboot.dao.EmployeeDao;

@Service
public class EmployeeDelegate {


@Autowired
EmployeeDao employeeDao;
	
public List <Employee>  viewEmployeeDetails() {
	List <Employee> employeeList = null;

	try {
		employeeList = employeeDao.viewEmployeeDetails();

	} catch (Exception e) {
		System.out.println("exception here");
	}
	return employeeList;
}


public List <Employee>  viewEmployeeDetailsById(int id) {
	List <Employee> employeeList = null;

	try {
		employeeList = employeeDao.viewEmployeeDetailsById(id);

	} catch (Exception e) {
		System.out.println("exception here");
	}
	return employeeList;
}

	
	public Employee  registerEmployee(Employee employee) {
		Employee employees = null;
		try {
			employees = employeeDao.registerEmployee(employee);

		} catch (SQLException e) {
			System.out.println("exception here");
		}
		return employees;
	}
	
public Employee updateEmployee(int id ,Employee employee) {
	
	Employee employees = null;
		try {
			employees = employeeDao.updateEmployee(id,employee);

		} catch (SQLException e) {
			System.out.println("exception here");
		}
		return employees;
	}
	
public boolean deleteEmployee(int id) {
	
	boolean flag = false;
	try {
		flag = employeeDao.deleteEmployee(id);

	} catch (SQLException e) {
		System.out.println("exception here");
	}
	return flag;
}

}
