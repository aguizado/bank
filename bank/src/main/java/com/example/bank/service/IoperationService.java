package com.example.bank.service;

import com.example.bank.model.dto.OperationDto;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface IoperationService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
public interface IoperationService {

  public Single<OperationDto> createOperation(OperationDto operationDto);
  
  public Observable<OperationDto> getMovements(Integer customerId);
  
  public Single<OperationDto> getReportLastMovements(Integer customerId);
  
  public Observable<OperationDto> getCommissions(Integer customerId);

}
