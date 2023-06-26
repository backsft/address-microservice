package com.seleniumexpress.address.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seleniumexpress.address.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	
	List<Address> findByEmpId(int empId);
	
	Address findByZip(long zip);
	
	
	
	
	
	
	
	
}



