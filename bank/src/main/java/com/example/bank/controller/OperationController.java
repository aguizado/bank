package com.example.bank.controller;

import com.example.bank.model.dto.CustomerDto;
import com.example.bank.model.dto.CustomerProductDto;
import com.example.bank.model.dto.OperationDto;
import com.example.bank.model.dto.OperationTypeDto;
import com.example.bank.service.IcustomerProductService;
import com.example.bank.service.IoperationService;
import com.example.bank.service.IoperationTypeService;
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
 * . Class OperationController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/20
 */
@RestController
@Log4j2
@RequiredArgsConstructor
public class OperationController {
  
  @Autowired
  IoperationService operationService;
  
  @Autowired
  IoperationTypeService operationTypeService;
  
  @Autowired
  IcustomerProductService customerProductService;
  
  /**
   * . This method is to save Operation
   *
   * @param operation This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/operation")
  public ResponseEntity<OperationDto> createOperation(
      @RequestBody OperationDto operation) {
    log.info("Validate Operation Type");
    Optional<OperationTypeDto> opOperationType = operationTypeService
        .getOperationType(operation.getTypeOperation().getId());
    log.info("Validate Customer Product");
    Optional<CustomerProductDto> opCustomerProduct = customerProductService
        .getProducts(operation.getCustomerProducto().getCustomer().getId());
    if (opOperationType.isPresent() && opCustomerProduct.isPresent()) {
      OperationDto operationDto = operationService.createOperation(operation);
      return new ResponseEntity<>(operationDto, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  /**
   * . This method is to get Movements of Operation
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/operation/check_movements/{customerId}")
  public ResponseEntity<OperationDto> getMovements(
      @PathVariable("customerId") Integer customerId) {
    Optional<CustomerProductDto> opCustomerProduct = customerProductService
        .getProducts(customerId);
    if (opCustomerProduct.isPresent()) {
      Optional<OperationDto> opOperation = operationService
          .getMovements(customerId);
      return ResponseEntity.status(HttpStatus.OK).body(opOperation.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
  
  /**
   * . This method is to get Movements of Operation
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/operation/get_report_last_movements/{customerId}")
  public ResponseEntity<OperationDto> getReportLastMovements(
      @PathVariable("customerId") Integer customerId) {
    Optional<CustomerProductDto> opCustomerProduct = customerProductService
        .getProducts(customerId);
    if (opCustomerProduct.isPresent()) {
      Optional<OperationDto> opOperation = operationService
          .getMovements(customerId);
      return ResponseEntity.status(HttpStatus.OK).body(opOperation.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
