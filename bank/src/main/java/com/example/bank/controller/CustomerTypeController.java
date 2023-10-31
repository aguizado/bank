package com.example.bank.controller;

import com.example.bank.model.dto.CustomerTypeDto;
import com.example.bank.service.IcustomerTypeService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
  public Single<CustomerTypeDto> createCustomerType(
      @RequestBody CustomerTypeDto customerType) {
    return customerTypeService.createCustomerType(customerType);
  }
  
  /**
   * . This method is to get Customer Type
   *
   * @return a HTTP Status
   */
  @GetMapping("/customer_type/getAll")
  public Observable<CustomerTypeDto> getCustomerTypes() {
    return customerTypeService.getCustomerTypes();
  }
  
  /**
   * . This method is to get Customer Type
   *
   * @param customerTypeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customer_type/{customerTypeId}")
  public Single<CustomerTypeDto> getCustomerType(
      @PathVariable("customerTypeId") Integer customerTypeId) {
    return customerTypeService.getCustomerType(customerTypeId);
  }
  
  /**
   * . This method is to update Customer Type
   *
   * @param customerTypeId This is the first parameter
   * @param customerType   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/customer_type/{customerTypeId}")
  public Single<CustomerTypeDto> editCustomerType(
      @PathVariable("customerTypeId") Integer customerTypeId,
      @RequestBody CustomerTypeDto customerType) {
    return customerTypeService.editCustomerType(customerType);
  }

}
