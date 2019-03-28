package com.zilker.jpa.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zilker.jpa.beans.EmployeeSpeciality;

@Repository
public interface EmployeeSpecialityRepository extends JpaRepository<EmployeeSpeciality, Integer> {

	boolean existsByEmployeeIdAndSpecialityId(@Param("employee_id") int employee_id ,@Param("speciality_id") int speciality_id);
		
	@Transactional
	void deleteByEmployeeIdAndSpecialityId(@Param("employee_id") int employee_id ,@Param("speciality_id") int speciality_id);
}
