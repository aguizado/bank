package com.example.bank.controller;

import com.example.bank.model.OperationModel;
import com.example.bank.service.IoperationService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class OperationController {

  @Autowired
  IoperationService operationService;

  /**
   * . This method is to create Operation
   *
   * @param operation This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/transactions")
  public ResponseEntity<OperationModel> createOperation(@RequestBody OperationModel operation) {
    try {
      OperationModel operationModel = operationService.createOperation(operation);
      return new ResponseEntity<>(operationModel, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * . This method is to get Operation
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/transactions/{customerId}")
  public ResponseEntity<OperationModel> getOperation(
      @PathVariable("customerId") Integer customerId) {
    Optional<OperationModel> opCustomer = operationService.getOperation(customerId);
    if (opCustomer.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opCustomer.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
