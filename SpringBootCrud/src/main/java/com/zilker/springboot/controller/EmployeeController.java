package com.zilker.springboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.zilker.springboot.beans.Employee;
import com.zilker.springboot.delegate.EmployeeDelegate;


@RestController

public class EmployeeController {

@Autowired
EmployeeDelegate employeeDelegate;
	
	
	@GetMapping("/employees")
	public List <Employee> viewEmployeeDetails() {
		List <Employee> employeeList = null;
		
		employeeList = employeeDelegate.viewEmployeeDetails();
		
		return employeeList;
	}

	@GetMapping("/employees/{id}")
	public List <Employee> viewEmployeeDetailsById(@PathVariable("id") int id) {
		List <Employee> employeeList = null;
		
		employeeList = employeeDelegate.viewEmployeeDetailsById(id);
		
		return employeeList;
	}
	
	@PostMapping("/employees")
	public Employee registerEmployee(@RequestBody Employee employee) {
		Employee employees = null;
	
		try {
		employees  = employeeDelegate.registerEmployee(employee);
			
		}catch(Exception e) {
			
		}
		
		return  employees;
	}

	@PutMapping("/employees/{id}")
	public Employee  updateEmployee(@PathVariable("id") int id,@RequestBody Employee employee) {
		
		Employee employees = null;
		try {
			 employees = employeeDelegate.updateEmployee(id,employee);
		
		}catch(Exception e) {
			
		}
		
		return employees ;
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		
		
		String message="";
		boolean flag=false;
		try {
		flag = employeeDelegate.deleteEmployee(id);
		if(flag==true) {
		 message= "success";
		}else {
			message="failed";
		}
		}catch(Exception e) {
			
		}
		
		return message;
	}
	
}
