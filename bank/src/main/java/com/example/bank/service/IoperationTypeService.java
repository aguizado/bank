package com.example.bank.service;

import com.example.bank.model.dto.OperationTypeDto;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * . Interface IoperationTypeService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
public interface IoperationTypeService {
  
  public Single<OperationTypeDto> createOperationType(OperationTypeDto operationType);
  
  public Observable<OperationTypeDto> getOperationTypes();
  
  public Single<OperationTypeDto> getOperationType(Integer operationTypeId);
  
  public Single<OperationTypeDto> editOperationType(OperationTypeDto operationType);

}
