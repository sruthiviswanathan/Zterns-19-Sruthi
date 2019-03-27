package com.zilker.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zilker.jpa.beans.Department;
import com.zilker.jpa.beans.Employee;
import com.zilker.jpa.customException.ApplicationException;
import com.zilker.jpa.customException.DepartmentNotFoundException;
import com.zilker.jpa.repository.DepartmentRepository;
import com.zilker.jpa.repository.EmployeeRepository;

@Repository
public class DepartmentDao {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	
	public List<Employee> viewEmployeesInDepartment(String department) throws ApplicationException{
		// TODO Auto-generated method stub
		List <Employee> employeeList = new ArrayList<Employee>();
		Department departmentObject = new Department();
		boolean flag;
		try {
		
			flag = departmentRepository.existsByName(department);
			if(flag) {
				departmentObject =departmentRepository.findByName(department);
				employeeList = employeeRepository.findByDepartment(departmentObject);				
				
			}else {
				throw new DepartmentNotFoundException();
			}
		}catch(DepartmentNotFoundException e) {
			throw e;
		}
		 
		catch(Exception e){
			throw new ApplicationException("SQL_EXP","SQLException");
		}
		return employeeList;
	}

}
