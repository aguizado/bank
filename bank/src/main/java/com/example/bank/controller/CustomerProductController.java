package com.example.bank.controller;

import com.example.bank.model.dto.CustomerProductDto;
import com.example.bank.service.IcustomerProductService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Class CustomerProductController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@RestController
@Log4j2
@RequiredArgsConstructor
public class CustomerProductController {
  
  @Autowired
  IcustomerProductService customerProductService;
  
  /**
   * . This method is to save Customer Product
   *
   * @param customerProduct This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/customer_product")
  public Single<CustomerProductDto> createCustomerProduct(
      @RequestBody CustomerProductDto customerProduct) {
    log.info("Save Customer Product");
    return customerProductService.createCustomerProduct(customerProduct);
  }
  
  /**
   * . This method is to get Balance of Customer Product
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customer_product/check_balance/{customerId}")
  public Single<CustomerProductDto> getBalance(
      @PathVariable("customerId") Integer customerId) {
    return customerProductService.getBalance(customerId);
  }
  
  /**
   * . This method is to get Product of Customer Product
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customer_product/check_products/{customerId}")
  public Single<CustomerProductDto> getProducts(
      @PathVariable("customerId") Integer customerId) {
    return customerProductService.getBalance(customerId);
  }

}
