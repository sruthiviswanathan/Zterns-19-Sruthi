package com.zilker.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zilker.jpa.beans.Department;
import com.zilker.jpa.beans.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	 @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Employee c WHERE c.email = :email")
	 boolean existsByEmail(@Param("email") String email);

	 @Query("SELECT d FROM Employee d WHERE d.department = ?1")
	 List<Employee> findByDepartment(@Param("department") Department department);
	 
	 @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Employee c WHERE c.id = :id")
	 boolean existsById(@Param("id") int id);
}
