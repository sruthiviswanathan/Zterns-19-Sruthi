package com.zilker.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zilker.jpa.beans.Employee;
import com.zilker.jpa.beans.Speciality;
import com.zilker.jpa.customException.ApplicationException;
import com.zilker.jpa.delegate.EmployeeDelegate;
import com.zilker.jpa.utils.ResponseGeneratorUtil;


@RestController
public class EmployeeController {

	@Autowired
	EmployeeDelegate employeeDelegate;	
	
	@Autowired
	ResponseGeneratorUtil reponseGeneratorUtil;
	
	@PostMapping("/employees")
	public <T> ResponseEntity<?>  registerEmployee(@RequestBody Employee employee) {
		Employee employees = null;
		
		try {
		employees  = employeeDelegate.registerEmployee(employee);
		
		return reponseGeneratorUtil.successResponse(employees);		
		
		}catch(ApplicationException e) {
			return reponseGeneratorUtil.errorResponse(e);	
		}
		
	}
	
	@GetMapping("/employees")
	public <T> ResponseEntity<?>  getAllEmployees() {
		List <Employee> employeeList = null;
		try {	
		
			employeeList = employeeDelegate.viewEmployeeDetails();
			return reponseGeneratorUtil.successResponse(employeeList);
		
		}catch(ApplicationException e) {
			return reponseGeneratorUtil.errorResponse(e);
		}
		
	}
	
	@GetMapping("/employees/{userId}")
	public <T> ResponseEntity<?>  getEmployeeById(@PathVariable("userId") int id) {
		List <Employee> employeeDetails = null;
		try {	
		
			employeeDetails = employeeDelegate.viewEmployeeDetailsById(id);
			return reponseGeneratorUtil.successResponse(employeeDetails);
		
		}catch(ApplicationException e) {
			return reponseGeneratorUtil.errorResponse(e);
		}
		
	}

	@PutMapping("/employees/{userId}")
	public <T> ResponseEntity<?>  UpdateEmployeeDetails(@PathVariable("userId") int id,@RequestBody Employee employee) {
		Employee employees = null;
		try {	
		
			employees = employeeDelegate.UpdateEmployeeDetails(id,employee);
			return reponseGeneratorUtil.successResponse(employees);
		
		}catch(ApplicationException e) {
			return reponseGeneratorUtil.errorResponse(e);
		}
		
	}
	
	@DeleteMapping("/employees/{userId}")
	public <T> ResponseEntity<?>  DeleteEmployeeDetails(@PathVariable("userId") int id) {
		String message = "";
		try {	
		
			message = employeeDelegate.DeleteEmployeeDetails(id);
			return reponseGeneratorUtil.successResponse(message);
		
		}catch(ApplicationException e) {
			return reponseGeneratorUtil.errorResponse(e);
		}
		
	}
	
	@PostMapping("employees/speciality/{userId}")
	public <T> ResponseEntity<?>  AddSpecialtyEmployee(@PathVariable("userId") int id,@RequestBody Speciality speciality) {
		boolean flag;
		
		try {
		flag  = employeeDelegate.saveSpeciality(id,speciality);
		if(flag==true) {
		return reponseGeneratorUtil.successResponse("SPECIALITY ADDED");		
		}else {
			return reponseGeneratorUtil.generateMessage("ERROR ADDING SPECIALITY");	
		}
		}catch(ApplicationException e) {
			return reponseGeneratorUtil.errorResponse(e);	
		}
		
	}
	
}
