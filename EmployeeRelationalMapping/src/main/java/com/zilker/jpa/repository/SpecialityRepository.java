package com.zilker.jpa.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.zilker.jpa.beans.Speciality;

public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {

	 @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Speciality c WHERE c.name = :name")
	  boolean existsByName(@Param("name") String name);
	
	@Query("SELECT d.id FROM Speciality d WHERE d.name = ?1")
	    int findByName(@Param("name") String name);
	
}
