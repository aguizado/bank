package com.example.bank.controller;

import com.example.bank.model.CustomerModel;
import com.example.bank.service.CustomerApiDelegate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Class CustomerController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/20
 */
@RestController
@RequiredArgsConstructor
public class CustomerController {

  @Autowired
  CustomerApiDelegate customerApiDelegate;

  /**
   * . This method is to save Customer
   *
   * @param customer This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/customers")
  public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customer) {
    try {
      CustomerModel customerModel = customerApiDelegate.createCustomer(customer);
      return new ResponseEntity<>(customerModel, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * . This method is to get Customer
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customers/{customerId}")
  public ResponseEntity<CustomerModel> getCustomer(
      @PathVariable("customerId") Integer customerId) {
    Optional<CustomerModel> opCustomer = customerApiDelegate.getCustomer(customerId);
    if (opCustomer.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opCustomer.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  /**
   * . This method is to update Customer
   *
   * @param customerId This is the first parameter
   * @param customer   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/customers/{customerId}")
  public ResponseEntity<CustomerModel> editCustomer(
      @PathVariable("customerId") Integer customerId,
      @RequestBody CustomerModel customer) {
    Optional<CustomerModel> opCustomer = customerApiDelegate.getCustomer(customerId);
    if (opCustomer.isPresent()) {
      return new ResponseEntity<>(customerApiDelegate.editCustomer(customer), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  /**
   * . This method is to delete Customer
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @DeleteMapping("/customers/{customerId}")
  public ResponseEntity<HttpStatus> deleteCustomer(
      @PathVariable("customerId") Integer customerId) {
    try {
      customerApiDelegate.deleteCustomer(customerId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
