package com.example.bank.controller;

import com.example.bank.model.dto.CardDto;
import com.example.bank.model.dto.CustomerDto;
import com.example.bank.model.dto.CustomerProductDto;
import com.example.bank.model.dto.ProductDto;
import com.example.bank.model.dto.RepresentativeDto;
import com.example.bank.service.IcardService;
import com.example.bank.service.IcustomerProductService;
import com.example.bank.service.IcustomerService;
import com.example.bank.service.IproductService;
import com.example.bank.service.IrepresentativeService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  
  @Autowired
  IcustomerService customerService;
  
  @Autowired
  IproductService productService;
  
  @Autowired
  IrepresentativeService representativeService;
  
  @Autowired
  IcardService cardService;
  
  /**
   * . This method is to save Customer Product
   *
   * @param customerProduct This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/customer_product")
  public ResponseEntity<CustomerProductDto> createCustomerProduct(
      @RequestBody CustomerProductDto customerProduct) {
    log.info("Validate Customer");
    Optional<CustomerDto> opCustomer = customerService
        .getCustomer(customerProduct.getCustomer().getId());
    log.info("Validate Product");
    Optional<ProductDto> opProduct = productService
        .getProduct(customerProduct.getProduct().getId());
    log.info("Validate Representative");
    Optional<RepresentativeDto> opRepresentative = representativeService
        .getRepresentative(customerProduct.getRepresentative().getId());
    if (opCustomer.isPresent() && opProduct.isPresent() 
        && opRepresentative.isPresent()) {
      CustomerProductDto customerProductDto = customerProductService
          .createCustomerProduct(customerProduct);
      return new ResponseEntity<>(customerProductDto, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  /**
   * . This method is to get Balance of Customer Product
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customer_product/check_balance/{customerId}")
  public ResponseEntity<CustomerProductDto> getBalance(
      @PathVariable("customerId") Integer customerId) {
    Optional<CustomerDto> opCustomer = customerService
        .getCustomer(customerId);
    if (opCustomer.isPresent()) {
      Optional<CustomerProductDto> opCustomerProduct = customerProductService
          .getBalance(customerId);
      return ResponseEntity.status(HttpStatus.OK).body(opCustomerProduct.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
  
  /**
   * . This method is to get Product of Customer Product
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customer_product/check_products/{customerId}")
  public ResponseEntity<CustomerProductDto> getProducts(
      @PathVariable("customerId") Integer customerId) {
    Optional<CustomerDto> opCustomer = customerService
        .getCustomer(customerId);
    if (opCustomer.isPresent()) {
      Optional<CustomerProductDto> opCustomerProduct = customerProductService
          .getProducts(customerId);
      return ResponseEntity.status(HttpStatus.OK).body(opCustomerProduct.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
