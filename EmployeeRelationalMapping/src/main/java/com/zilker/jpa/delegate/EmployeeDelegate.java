package com.zilker.jpa.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zilker.jpa.beans.Employee;
import com.zilker.jpa.beans.Speciality;
import com.zilker.jpa.customException.ApplicationException;
import com.zilker.jpa.customException.DepartmentNotFoundException;
import com.zilker.jpa.customException.EmailAlreadyExistsException;
import com.zilker.jpa.customException.EmployeeNotFoundException;
import com.zilker.jpa.dao.EmployeeDao;


@Service
public class EmployeeDelegate {

	@Autowired
	EmployeeDao employeeDao;	
	
	public Employee registerEmployee(Employee employee)throws ApplicationException {
		// TODO Auto-generated method stub
		Employee employees = null;
		try {
			employees = employeeDao.registerEmployee(employee);
			if(employees != null) {
				
			}else {
				throw new DepartmentNotFoundException();
			}

		} catch (DepartmentNotFoundException e) {
			throw e;
		}catch(EmailAlreadyExistsException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ApplicationException("Exception","Exception");
		}
		return employees;
	}

	public List<Employee> viewEmployeeDetails() throws ApplicationException {
		// TODO Auto-generated method stub
		List <Employee> employeeList = null;

		try {
			employeeList = employeeDao.viewEmployeeDetails();

		} catch (Exception e) {
			throw new ApplicationException("Exception","Exception");
		}
		return employeeList;
	}

	public List<Employee> viewEmployeeDetailsById(int id) throws ApplicationException {
		// TODO Auto-generated method stub
		List<Employee> employee = null;
		try {
			employee = employeeDao.viewEmployeeDetailsById(id);
			if(employee.isEmpty()) {
				throw new EmployeeNotFoundException();
			}
		}catch(EmployeeNotFoundException e) {
		
			throw e;
		
		}catch(Exception e) {
			
			throw new ApplicationException("Exception","Exception");
		}
		return employee;
	}

	public Employee UpdateEmployeeDetails(int id,Employee employee) throws ApplicationException {
		// TODO Auto-generated method stub
		Employee employees = null;
		try {
			employees = employeeDao.UpdateEmployeeDetails(id,employee);
			if(employees != null) {
				
			}else {
				throw new DepartmentNotFoundException();
			}

		} catch (DepartmentNotFoundException e) {
			throw e;
		}catch(EmailAlreadyExistsException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ApplicationException("Exception","Exception");
		}
		return employees;
	}

	public String DeleteEmployeeDetails(int id) throws ApplicationException {
		// TODO Auto-generated method stub
		String message="";
		try {
			
			message = employeeDao.DeleteEmployeeDetails(id);
			
		}catch(Exception e) {
			throw new ApplicationException("Exception","Exception");
		}
		return message;
	}

	public boolean saveSpeciality(int id,Speciality speciality)throws ApplicationException {
		// TODO Auto-generated method stub
		boolean flag;
		try {
			flag = employeeDao.saveSpeciality(id,speciality);
		
		} catch (EmployeeNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ApplicationException("Exception","Exception");
		}
		return flag;
	}

}
