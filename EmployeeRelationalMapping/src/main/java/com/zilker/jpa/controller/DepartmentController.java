package com.zilker.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zilker.jpa.beans.Employee;
import com.zilker.jpa.customException.ApplicationException;
import com.zilker.jpa.delegate.DepartmentDelegate;
import com.zilker.jpa.utils.ResponseGeneratorUtil;

@RestController
public class DepartmentController {


	@Autowired
	DepartmentDelegate departmentDelegate;
	
	@Autowired
	ResponseGeneratorUtil reponseGeneratorUtil;
	
	@GetMapping("/department/{name}")
	public <T> ResponseEntity<?>  getAllEmployeesByDepartment(@PathVariable("name") String department) throws ApplicationException {
		List <Employee> employeeList = null;
		try {	
		
			employeeList = departmentDelegate.viewEmployeesInDepartment(department);
			return reponseGeneratorUtil.successResponse(employeeList);
		
		}catch(ApplicationException e) {
			return reponseGeneratorUtil.errorResponse(e);
		}
		
	}
}
