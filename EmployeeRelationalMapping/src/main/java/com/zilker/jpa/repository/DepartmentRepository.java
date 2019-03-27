package com.zilker.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zilker.jpa.beans.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	 @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Department c WHERE c.name = :name")
	    boolean existsByName(@Param("name") String name);
	
	 @Query("SELECT d FROM Department d WHERE d.name = ?1")
	    Department findByName(@Param("name") String name);
}
