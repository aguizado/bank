package com.example.bank.controller;

import com.example.bank.model.CustomerModel;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.service.CustomerApiDelegate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**.
* Class CustomerController
*
* @author Andres Guizado
* @version 0.1, 2023/10/20
*/
@RestController
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerRepository customerRepository;
	CustomerApiDelegate customerApiDelegate;
	
	@PostMapping("/customers")
	public ResponseEntity<CustomerModel> customersPost(@RequestBody CustomerModel customer) {
		try {
			CustomerModel customerModel = customerApiDelegate.createCustomer(customer);
			return new ResponseEntity<>(customerModel, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<CustomerModel> customersCustomerIdGet(@PathVariable("customerId") Integer customerId) {
		Optional<CustomerModel> opCustomer = customerRepository.findById(customerId);
		if (opCustomer.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(opCustomer.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PutMapping("/customers/{customerId}")
	public ResponseEntity<CustomerModel> customersCustomerIdPut(@PathVariable("customerId") Integer customerId, @RequestBody CustomerModel customer) {
		Optional<CustomerModel> opCustomer = customerRepository.findById(customerId);
		if (opCustomer.isPresent()) {
			return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
//	@DeleteMapping("/customers/{customerId}")
//	public ResponseEntity<HttpStatus> customersCustomerIdDelete(@PathVariable("customerId") Integer customerId) {
//		try {
//			customerRepository.deleteById(customerId);
//		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}
