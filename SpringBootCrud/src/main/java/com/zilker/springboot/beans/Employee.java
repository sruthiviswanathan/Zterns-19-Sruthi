package com.zilker.springboot.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	private int id;
	
	@Column(name="firstname",length=40)
	private String firstName;
	
	@Column(name="lastname",length=40)
	private String lastName;
	
	@Column(name="email",length=50)
	private String email;
	
	@Column(name="phone_number")
	private long phoneNumber;
	
	@Column(name="salary")
	private long salary;

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
	
	public String toString() 
    { 
        return firstName + " " + lastName + " " + phoneNumber + " " + email + " " + salary; 
    } 
	
	
}
