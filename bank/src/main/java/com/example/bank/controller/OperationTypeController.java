package com.example.bank.controller;

import com.example.bank.model.dto.OperationTypeDto;
import com.example.bank.service.IoperationTypeService;
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
@Log4j2
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
  public Single<ResponseEntity<OperationTypeDto>> createOperationType(
      @RequestBody OperationTypeDto operationType) {
    return operationTypeService.createOperationType(operationType)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to get Operation Type
   *
   * @return a HTTP Status
   */
  @GetMapping("/operation_type/getAll")
  public Observable<ResponseEntity<OperationTypeDto>> getOperationTypes() { 
    return operationTypeService.getOperationTypes()
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable));
  }
  
  /**
   * . This method is to get Operation Type
   *
   * @param operationTypeId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/operation_type/{operationTypeId}")
  public Single<ResponseEntity<OperationTypeDto>> getOperationType(
      @PathVariable("operationTypeId") Integer operationTypeId) {
    return operationTypeService.getOperationType(operationTypeId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to update Operation Type
   *
   * @param operationTypeId This is the first parameter
   * @param operationType   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/operation_type/{operationTypeId}")
  public Single<ResponseEntity<OperationTypeDto>> editOperationType(
      @PathVariable("operationTypeId") Integer operationTypeId,
      @RequestBody OperationTypeDto operationType) {
    return operationTypeService.editOperationType(operationType)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

}
