package com.example.bank.controller;

import com.example.bank.model.dto.CustomerDto;
import com.example.bank.service.IcustomerService;
import com.example.bank.util.Constants;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
@Log4j2
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
  public Single<ResponseEntity<CustomerDto>> createCustomer(@RequestBody CustomerDto customer) {
    return customerService.createCustomer(customer)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

  /**
   * . This method is to get Customer
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customer/{customerId}")
  public Single<ResponseEntity<CustomerDto>> getCustomer(
      @PathVariable("customerId") Integer customerId) {
    return customerService.getCustomer(customerId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

  /**
   * . This method is to update Customer
   *
   * @param customerId This is the first parameter
   * @param customer   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/customer/{customerId}")
  public Single<ResponseEntity<CustomerDto>> editCustomer(
      @PathVariable("customerId") Integer customerId,
      @RequestBody CustomerDto customer) {
    return customerService.editCustomer(customer)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

  /**
   * . This method is to delete Customer
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @DeleteMapping("/customer/{customerId}")
  public Single<ResponseEntity<CustomerDto>> deleteCustomer(
      @PathVariable("customerId") Integer customerId) {
    return customerService.deleteCustomer(customerId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

}
