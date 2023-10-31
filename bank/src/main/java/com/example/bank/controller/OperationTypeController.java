package com.example.bank.controller;

import com.example.bank.model.dto.OperationTypeDto;
import com.example.bank.service.IoperationTypeService;
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
  public Single<OperationTypeDto> createOperationType(
      @RequestBody OperationTypeDto operationType) {
    return operationTypeService.createOperationType(operationType);
  }
  
  /**
   * . This method is to get Operation Type
   *
   * @return a HTTP Status
   */
  @GetMapping("/operation_type/getAll")
  public Observable<OperationTypeDto> getOperationTypes() { 
    return operationTypeService.getOperationTypes();
  }
  
  /**
   * . This method is to get Operation Type
   *
   * @param operationTypeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/operation_type/{operationTypeId}")
  public Single<OperationTypeDto> getOperationType(
      @PathVariable("operationTypeId") Integer operationTypeId) {
    return operationTypeService.getOperationType(operationTypeId);
  }
  
  /**
   * . This method is to update Operation Type
   *
   * @param operationTypeId This is the first parameter
   * @param operationType   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/operation_type/{operationTypeId}")
  public Single<OperationTypeDto> editOperationType(
      @PathVariable("operationTypeId") Integer operationTypeId,
      @RequestBody OperationTypeDto operationType) {
    return operationTypeService.editOperationType(operationType);
  }

}
