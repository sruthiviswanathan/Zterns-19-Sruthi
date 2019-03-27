package com.zilker.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zilker.jpa.beans.EmployeeSpeciality;

@Repository
public interface EmployeeSpecialityRepository extends JpaRepository<EmployeeSpeciality, Integer> {

}
