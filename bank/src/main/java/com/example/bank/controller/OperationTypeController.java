package com.example.bank.controller;

import com.example.bank.model.dto.OperationTypeDto;
import com.example.bank.service.IoperationTypeService;
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
 * . Class OperationTypeController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@RestController
@RequiredArgsConstructor
public class OperationTypeController {
  
  @Autowired
  IoperationTypeService operationTypeService;
  
  /**
   * . This method is to save Operation Type
   *
   * @param operationType This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/operation_type")
  public ResponseEntity<OperationTypeDto> createOperationType(
      @RequestBody OperationTypeDto operationType) {
    try {
      OperationTypeDto operationTypeDto = operationTypeService.createOperationType(operationType);
      return new ResponseEntity<>(operationTypeDto, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  /**
   * . This method is to get Operation Type
   *
   * @return a HTTP Status
   */
  @GetMapping("/operation_type/getAll")
  public ResponseEntity<List<OperationTypeDto>> getOperationTypes() { 
    try {
      List<OperationTypeDto> operationTypeList = operationTypeService.getOperationTypes();
      return ResponseEntity.ok(operationTypeList);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
  
  /**
   * . This method is to get Operation Type
   *
   * @param operationTypeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/operation_type/{operationTypeId}")
  public ResponseEntity<OperationTypeDto> getOperationType(
      @PathVariable("operationTypeId") Integer operationTypeId) {
    Optional<OperationTypeDto> opOperationType = operationTypeService
        .getOperationType(operationTypeId);
    if (opOperationType.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opOperationType.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
  
  /**
   * . This method is to update Operation Type
   *
   * @param operationTypeId This is the first parameter
   * @param operationType   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/operation_type/{operationTypeId}")
  public ResponseEntity<OperationTypeDto> editOperationType(
      @PathVariable("operationTypeId") Integer operationTypeId,
      @RequestBody OperationTypeDto operationType) {
    Optional<OperationTypeDto> opOperationType = operationTypeService
        .getOperationType(operationTypeId);
    if (opOperationType.isPresent()) {
      return new ResponseEntity<>(operationTypeService
          .editOperationType(operationType), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
