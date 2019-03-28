package com.zilker.jpa.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	private int id;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private long phoneNumber;
	
	@Column(name="salary")
	private long salary;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
		
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="address_id")
	private Address address;
	

    @JsonIgnore
	@OneToMany(mappedBy="employeeId",cascade=CascadeType.REMOVE)
	private List<EmployeeSpeciality> specialityList;
	
	@Transient
	private List<Speciality> speciality;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
		
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public List<EmployeeSpeciality> getSpecialityList() {
		return specialityList;
	}
	public void setSpecialityList(List<EmployeeSpeciality> specialityList) {
		this.specialityList = specialityList;
	}
	
	public List<Speciality> getSpeciality() {
		return this.speciality;
	}
	public void setSpeciality(List<Speciality> speciality) {
		this.speciality = speciality;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", salary=" + salary + ", department=" + department + ", address="
				+ address + ", specialityList=" + specialityList + ", speciality=" + speciality + "]";
	}

	
	
	
	
	
}
