package com.zilker.jpa.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="employee_specialty")
@IdClass(EmployeeSplKey.class)
public class EmployeeSpeciality {

	
	
	@Id
	@Column(name="employee_id")
	private int employeeId;
	
	@Id
	@Column(name="specialty_id")
	private int specialityId;

	
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(int specialityId) {
		this.specialityId = specialityId;
	}

	@Override
	public String toString() {
		return "EmployeeSpeciality [employeeId=" + employeeId + ", specialityId=" + specialityId + "]";
	}

	

	
	
	
	
	
	
	
}
