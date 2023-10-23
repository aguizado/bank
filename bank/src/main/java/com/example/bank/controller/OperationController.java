package com.example.bank.controller;

import com.example.bank.model.OperationModel;
import com.example.bank.repository.OperationRepository;
import com.example.bank.service.OperationApiDelegate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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

  private final OperationRepository operationRepository;
  OperationApiDelegate operationApiDelegate;

  @PostMapping("/transactions")
  public ResponseEntity<OperationModel> transactionsPost(@RequestBody OperationModel operation) {
    try {
      OperationModel operationModel = operationApiDelegate.createOperation(operation);
      return new ResponseEntity<>(operationModel, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/transactions/{customerId}")
  public ResponseEntity<OperationModel> transactionsCustomerIdGet(@PathVariable("customerId") Integer customerId) {
    Optional<OperationModel> opCustomer = operationRepository.findById(customerId);
    if (opCustomer.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opCustomer.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
