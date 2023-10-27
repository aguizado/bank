package com.example.bank.controller;

import com.example.bank.model.dto.CustomerTypeDto;
import com.example.bank.model.dto.ProfileDto;
import com.example.bank.service.IcustomerTypeService;
import com.example.bank.service.IprofileService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequiredArgsConstructor
public class CustomerTypeController {
  
  @Autowired
  IcustomerTypeService customerTypeService;
  
  @Autowired
  IprofileService profileService;
  
  /**
   * . This method is to save Customer Type
   *
   * @param customerType This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/customer_type")
  public ResponseEntity<CustomerTypeDto> createCustomerType(
      @RequestBody CustomerTypeDto customerType) {
    Optional<ProfileDto> opProfile = profileService.getProfile(
        customerType.getTypeProfile().getId());
    if (opProfile.isPresent()) {
      CustomerTypeDto customerTypeDto = customerTypeService.createCustomerType(customerType);
      return new ResponseEntity<>(customerTypeDto, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  /**
   * . This method is to get Customer Type
   *
   * @return a HTTP Status
   */
  @GetMapping("/customer_type/getAll")
  public ResponseEntity<List<CustomerTypeDto>> getCustomerTypes() { 
    try {
      List<CustomerTypeDto> customerTypeList = customerTypeService.getCustomerTypes();
      return ResponseEntity.ok(customerTypeList);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
  
  /**
   * . This method is to get Customer Type
   *
   * @param customerTypeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/customer_type/{customerTypeId}")
  public ResponseEntity<CustomerTypeDto> getCustomerType(
      @PathVariable("customerTypeId") Integer customerTypeId) {
    Optional<CustomerTypeDto> opCustomerType = customerTypeService
        .getCustomerType(customerTypeId);
    if (opCustomerType.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opCustomerType.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
  
  /**
   * . This method is to update Customer Type
   *
   * @param customerTypeId This is the first parameter
   * @param customerType   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/customer_type/{customerTypeId}")
  public ResponseEntity<CustomerTypeDto> editCustomerType(
      @PathVariable("customerTypeId") Integer customerTypeId,
      @RequestBody CustomerTypeDto customerType) {
    Optional<CustomerTypeDto> opCustomerType = customerTypeService
        .getCustomerType(customerTypeId);
    if (opCustomerType.isPresent()) {
      return new ResponseEntity<>(customerTypeService
          .editCustomerType(customerType), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
