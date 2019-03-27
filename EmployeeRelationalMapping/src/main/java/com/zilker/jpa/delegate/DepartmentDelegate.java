package com.zilker.jpa.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zilker.jpa.beans.Employee;
import com.zilker.jpa.customException.ApplicationException;
import com.zilker.jpa.customException.DepartmentNotFoundException;
import com.zilker.jpa.customException.EmployeeNotFoundException;
import com.zilker.jpa.dao.DepartmentDao;

@Service
public class DepartmentDelegate {

	@Autowired
	DepartmentDao departmentDao;
	
	public List<Employee> viewEmployeesInDepartment(String department) throws ApplicationException {
		// TODO Auto-generated method stub
		List <Employee> employeeList = null;

		try {
			employeeList = departmentDao.viewEmployeesInDepartment(department);
			if(employeeList.isEmpty()) {
				throw new EmployeeNotFoundException();
			}		
		}catch(EmployeeNotFoundException e) {
			throw e;
		}
		catch (DepartmentNotFoundException e) {
			throw e;
		} 
		
		catch (Exception e) {
			throw new ApplicationException("Exception","Exception");
		}
		return employeeList;
	}

}
