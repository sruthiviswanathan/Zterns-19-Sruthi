package com.zilker.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zilker.jpa.beans.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>  {

}
