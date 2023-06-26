package com.seleniumexpress.address.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.EceptionHandleTest.DatabaseConnectionFailedException;
import com.seleniumexpress.address.entity.Address;
import com.seleniumexpress.address.repository.AddressRepository;
import com.seleniumexpress.address.response.AddressResponse;

@RestController
public class AddressController {
	
	
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	ModelMapper modelMapper;
	
	
	@GetMapping("/address/{empId}")
	public List<AddressResponse>   getAddressByEmployeeId(@PathVariable int empId) throws Exception{
		
		
			List<Address> findByEmpId = addressRepository.findByEmpId(empId);
			
			//List<AddressResponse>  addressResponse = Arrays.asList(modelMapper.map(findByEmpId, AddressResponse.class)) ;
			 List<AddressResponse> addressResponseList = findByEmpId.stream()
			            .map(address -> modelMapper.map(address, AddressResponse.class))
			            .collect(Collectors.toList());
			
			
			return addressResponseList;
		
	
	}
	
	
	
	
	
	
	
	//by zip
	@GetMapping("/addresszip/{zip}")
	public Address  getAddressByZip(@PathVariable long zip) {
		
		Address findByZip = addressRepository.findByZip(zip);
		
		
		return findByZip;
	}
	

	@GetMapping("/address433/{empId}")
    public ResponseEntity<?> incrementByOne(@PathVariable int empId) {
        try {
        	addressRepository.findById(empId);
        } catch (DataAccessException  e) {
           
        	throw new DatabaseConnectionFailedException(e.getMessage());
            
        }
        return ResponseEntity.ok().build();
    }
	
	
}
