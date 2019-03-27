package com.zilker.jpa.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zilker.jpa.beans.Address;
import com.zilker.jpa.beans.Department;
import com.zilker.jpa.beans.Employee;
import com.zilker.jpa.beans.EmployeeSpeciality;
import com.zilker.jpa.beans.Speciality;
import com.zilker.jpa.customException.ApplicationException;
import com.zilker.jpa.customException.EmailAlreadyExistsException;
import com.zilker.jpa.customException.EmployeeNotFoundException;
import com.zilker.jpa.repository.AddressRepository;
import com.zilker.jpa.repository.DepartmentRepository;
import com.zilker.jpa.repository.EmployeeRepository;
import com.zilker.jpa.repository.EmployeeSpecialityRepository;
import com.zilker.jpa.repository.SpecialityRepository;



@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private SpecialityRepository specialityRepository;
	@Autowired
	private EmployeeSpecialityRepository empspecialityRepository;
	
	
	public Employee registerEmployee(Employee employee)throws ApplicationException {
		// TODO Auto-generated method stub
		Employee employees = new Employee();
		Address address = new Address();
		Department department = new Department();
		boolean flag=false;
		boolean emailExists=false;
		try {
			emailExists = employeeRepository.existsByEmail(employee.getEmail()); 
			if(emailExists) {
				throw new EmailAlreadyExistsException();
			}else {
				flag = departmentRepository.existsByName(employee.getDepartment().getName());
			if(flag) {
				department=departmentRepository.findByName(employee.getDepartment().getName());
				address = addressRepository.save(employee.getAddress());
				employee.setDepartment(department);
				employee.setAddress(address);	
				employees = employeeRepository.save(employee);
			}else {
				employees= null;
			}
			}
			
			}catch(EmailAlreadyExistsException e) {	
				throw e;
			}
			catch(Exception e) {			
				throw new ApplicationException("SQL_EXP","SQLException");
			
			}
		return employees;
	}

	public List<Employee> viewEmployeeDetails() throws ApplicationException {
		// TODO Auto-generated method stub
		List <Employee> employeeList = new ArrayList<Employee>();

		try {
			employeeList = employeeRepository.findAll();

		} catch (Exception e) {
			throw new ApplicationException("SQL_EXP","SQLException");
		}
		return employeeList;
	}

	public List <Employee> viewEmployeeDetailsById(int id) throws ApplicationException {
		// TODO Auto-generated method stub
		List <Employee> employeeDetails = new ArrayList<Employee>();
		Optional <Employee> optionalEmployeeList;

		try {
			if(employeeRepository.existsById(id)) {
			optionalEmployeeList = employeeRepository.findById(id);
			optionalEmployeeList.ifPresent(employeeDetails::add);
			}else {
				throw new EmployeeNotFoundException();
			}
		}catch (EmployeeNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ApplicationException("SQL_EXP","SQLException");
		}
		return employeeDetails;
	}

	public Employee UpdateEmployeeDetails(int id, Employee employee) throws ApplicationException {
		// TODO Auto-generated method stub
		Employee employees = new Employee();
		Address address = new Address();
		Department department = new Department();
		boolean flag=false;
		boolean emailExists=false;
		try {
			if(employeeRepository.existsById(id)) {
			emailExists = employeeRepository.existsByEmail(employee.getEmail()); 
			if(emailExists) {
				throw new EmailAlreadyExistsException();
			}else {
			flag = departmentRepository.existsByName(employee.getDepartment().getName());
			if(flag) {
				department=departmentRepository.findByName(employee.getDepartment().getName());
				employee.setId(id);
				address = addressRepository.save(employee.getAddress());
				employee.setDepartment(department);
				employee.setAddress(address);	
				employees = employeeRepository.save(employee);
			}
			}
			}else {
				throw new EmployeeNotFoundException();
			}
			}catch (EmployeeNotFoundException e) {
				throw e;
			}
			catch(EmailAlreadyExistsException e) {
				throw e;
			}
			catch(Exception e) {
				throw new ApplicationException("SQL_EXP","SQLException");
			
			}
		return employees;
	}

	public String DeleteEmployeeDetails(int id) throws ApplicationException {
		// TODO Auto-generated method stub
		String message = "";
		try {
			if(employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			message="Success";
			}else {
				throw new EmployeeNotFoundException();
			}
		}catch (EmployeeNotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ApplicationException("SQL_EXP","SQLException");
		}
		return message;
	}

	public boolean saveSpeciality(int id,Speciality speciality)throws ApplicationException {
		// TODO Auto-generated method stub
		EmployeeSpeciality employees = new EmployeeSpeciality();
		int specialityId= 0;
		boolean flag,status;
		try {
			if(employeeRepository.existsById(id)) {
			employees.setEmployeeId(id);
			flag = specialityRepository.existsByName(speciality.getName());
			if(flag) {
				specialityId = specialityRepository.findByName(speciality.getName());
				employees.setSpecialityId(specialityId);
				employees = empspecialityRepository.save(employees);
				status=true;
			}else {
				speciality= specialityRepository.save(speciality);
				specialityId = specialityRepository.findByName(speciality.getName());
				employees.setSpecialityId(specialityId);
				employees = empspecialityRepository.save(employees);
				status=true;
			}
			}else {
				
				throw new EmployeeNotFoundException();
			}
			}catch (EmployeeNotFoundException e) {
				throw e;
			}catch(Exception e) {
				throw new ApplicationException("SQL_EXP","SQLException");
			
			}
		return status;
	}

}
