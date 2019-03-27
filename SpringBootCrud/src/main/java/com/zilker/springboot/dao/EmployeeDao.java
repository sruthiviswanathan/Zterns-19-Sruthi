package com.zilker.springboot.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zilker.springboot.beans.Employee;
import com.zilker.springboot.repository.EmployeeRepository;



@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee  registerEmployee(Employee employee)  throws SQLException{
		Employee employees = new Employee();
		try {
			
			employees = employeeRepository.save(employee);
			 if(employees != null) {
				
				
			 }
		
			}catch(Exception e) {
				System.out.println("exception");
			
			}
		return employees;
	}
	public Employee updateEmployee(int id,Employee employee)  throws SQLException{
		Employee employees = new Employee();
		try {
		
			employee.setId(id);
			 employees = employeeRepository.save(employee);
			
		
			}catch(Exception e) {
				System.out.println("exception");
			
			}
		return employees;
	}
	
	public boolean deleteEmployee(int id)  throws SQLException{
		boolean flag = false;
	
		try {
			
			 employeeRepository.deleteById(id);
			 flag=true;
			}catch(Exception e) {
				System.out.println("exception");
			
			}
		return flag;
	}
	
	
	
	public List <Employee>  viewEmployeeDetails() {
		List <Employee> employeeList = new ArrayList<Employee>();

		try {
			employeeList = employeeRepository.findAll();

		} catch (Exception e) {
			System.out.println("exception here");
		}
		return employeeList;
	}

	public List <Employee>  viewEmployeeDetailsById(int id) {
		List <Employee> employeeList = new ArrayList<Employee>();
		Optional <Employee> optionalEmployeeList;

		try {
			optionalEmployeeList = employeeRepository.findById(id);
			optionalEmployeeList.ifPresent(employeeList::add);

		} catch (Exception e) {
			System.out.println("exception here");
		}
		return employeeList;
	}
}
