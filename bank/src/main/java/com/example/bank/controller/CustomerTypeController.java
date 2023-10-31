package com.example.bank.controller;

import com.example.bank.model.dto.CustomerTypeDto;
import com.example.bank.service.IcustomerTypeService;
import com.example.bank.util.Constants;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Class CustomerTypeController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@RestController
@Log4j2
@RequiredArgsConstructor
public class CustomerTypeController {
  
  @Autowired
  IcustomerTypeService customerTypeService;
  
  /**
   * . This method is to save Customer Type
   *
   * @param customerType This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/customer_type")
  public Single<ResponseEntity<CustomerTypeDto>> createCustomerType(
      @RequestBody CustomerTypeDto customerType) {
    return customerTypeService.createCustomerType(customerType)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to get Customer Type
   *
   * @return a HTTP Status
   */
  @GetMapping("/customer_type/getAll")
  public Observable<ResponseEntity<CustomerTypeDto>> getCustomerTypes() {
    return customerTypeService.getCustomerTypes()
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable));
  }
  
  /**
   * . This method is to get Customer Type
   *
   * @param customerTypeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customer_type/{customerTypeId}")
  public Single<ResponseEntity<CustomerTypeDto>> getCustomerType(
      @PathVariable("customerTypeId") Integer customerTypeId) {
    return customerTypeService.getCustomerType(customerTypeId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to update Customer Type
   *
   * @param customerTypeId This is the first parameter
   * @param customerType   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/customer_type/{customerTypeId}")
  public Single<ResponseEntity<CustomerTypeDto>> editCustomerType(
      @PathVariable("customerTypeId") Integer customerTypeId,
      @RequestBody CustomerTypeDto customerType) {
    return customerTypeService.editCustomerType(customerType)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

}
