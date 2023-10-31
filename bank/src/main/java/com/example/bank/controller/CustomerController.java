package com.example.bank.controller;

import com.example.bank.model.dto.CustomerDto;
import com.example.bank.service.IcustomerService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
  IcustomerService customerService;

  /**
   * . This method is to save Customer
   *
   * @param customer This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/customer")
  public Single<CustomerDto> createCustomer(@RequestBody CustomerDto customer) {
    return customerService.createCustomer(customer);
  }

  /**
   * . This method is to get Customer
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customer/{customerId}")
  public Single<CustomerDto> getCustomer(
      @PathVariable("customerId") Integer customerId) {
    return customerService.getCustomer(customerId);
  }

  /**
   * . This method is to update Customer
   *
   * @param customerId This is the first parameter
   * @param customer   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/customer/{customerId}")
  public Single<CustomerDto> editCustomer(
      @PathVariable("customerId") Integer customerId,
      @RequestBody CustomerDto customer) {
    return customerService.editCustomer(customer);
  }

  /**
   * . This method is to delete Customer
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @DeleteMapping("/customer/{customerId}")
  public Single<CustomerDto> deleteCustomer(
      @PathVariable("customerId") Integer customerId) {
    return customerService.deleteCustomer(customerId);
  }

}
