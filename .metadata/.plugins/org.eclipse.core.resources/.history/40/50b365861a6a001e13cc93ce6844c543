package com.example.bank.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.api.CustomersApiDelegate;
import com.example.bank.model.Customer;
import com.example.bank.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomersApiDelegateImpl implements CustomersApiDelegate{
	
	private final CustomerRepository customerRepository;
	
	@Override
	public ResponseEntity<Customer> customersPost(Customer customer){
		customerRepository.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@Override
	public ResponseEntity<Customer> customersCustomerIdGet(Integer customerId){		
		return customerRepository.findById(customerId).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@Override
	public ResponseEntity<Customer> customersCustomerIdPut(Integer customerId, Customer customer) {
		customerRepository.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@Override
	public ResponseEntity<Void> customersCustomerIdDelete(Integer customerId){
		customerRepository.deleteById(customerId);
		return  ResponseEntity.ok().build();
	}

}
