package com.example.bank.controller;

import com.example.bank.model.dto.OperationDto;
import com.example.bank.service.IoperationService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
  
  /**
   * . This method is to save Operation
   *
   * @param operation This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/operation")
  public Single<OperationDto> createOperation(
      @RequestBody OperationDto operation) {
    log.info("Operation");
    return operationService.createOperation(operation);
  }
  
  /**
   * . This method is to get Movements of Operation
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/operation/check_movements/{customerId}")
  public Single<OperationDto> getMovements(
      @PathVariable("customerId") Integer customerId) {
    return operationService.getMovements(customerId);
  }
  
  /**
   * . This method is to get Movements of Operation
   *
   * @param customerId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/operation/get_report_last_movements/{customerId}")
  public Single<OperationDto> getReportLastMovements(
      @PathVariable("customerId") Integer customerId) {
    return operationService.getReportLastMovements(customerId);
  }

}
