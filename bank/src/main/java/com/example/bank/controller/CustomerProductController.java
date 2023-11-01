package com.example.bank.controller;

import com.example.bank.model.dto.CustomerProductDto;
import com.example.bank.service.IcustomerProductService;
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
  public Single<ResponseEntity<CustomerProductDto>> createCustomerProduct(
      @RequestBody CustomerProductDto customerProduct) {
    return customerProductService.createCustomerProduct(customerProduct)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to get Balance of Customer Product
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customer_product/check_balance/{customerId}")
  public Single<ResponseEntity<CustomerProductDto>> getBalance(
      @PathVariable("customerId") Integer customerId) {
    return customerProductService.getBalance(customerId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to get Product of Customer Product
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customer_product/check_products/{customerId}")
  public Observable<ResponseEntity<CustomerProductDto>> getProducts(
      @PathVariable("customerId") Integer customerId) {
    return customerProductService.getProducts(customerId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable));
  }

}
