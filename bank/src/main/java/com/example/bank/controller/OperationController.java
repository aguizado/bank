package com.example.bank.controller;

import com.example.bank.model.dto.OperationDto;
import com.example.bank.service.IcustomerProductService;
import com.example.bank.service.IoperationService;
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
  IcustomerProductService customerProductService;
  
  /**
   * . This method is to save Operation
   *
   * @param operation This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/operation")
  public Single<ResponseEntity<OperationDto>> createOperation(
      @RequestBody OperationDto operation) {
         
    customerProductService.validateToUpdateOperation(operation)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response))
        .subscribe();
      
    return operationService.createOperation(operation)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to get Movements of Operation
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/operation/check_movements/{customerId}")
  public Observable<ResponseEntity<OperationDto>> getMovements(
      @PathVariable("customerId") Integer customerId) {
    return operationService.getMovements(customerId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnNext(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to get Movements of Operation
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/operation/get_report_last_movements/{customerId}")
  public Single<ResponseEntity<OperationDto>> getReportLastMovements(
      @PathVariable("customerId") Integer customerId) {
    return operationService.getReportLastMovements(customerId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnSuccess(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }
  
  /**
   * . This method is to get commissions charged per product in the current month
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/operation/get_commissions/{customerId}")
  public Observable<ResponseEntity<OperationDto>> getReportCommissions(
      @PathVariable("customerId") Integer customerId) {
    return operationService.getCommissions(customerId)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> log.error(Constants.DO_ON_ERROR, throwable))
        .doOnNext(response -> log.info(Constants.DO_ON_SUCCESS, response));
  }

}
