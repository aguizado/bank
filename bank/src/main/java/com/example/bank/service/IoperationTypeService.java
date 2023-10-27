package com.example.bank.service;

import com.example.bank.model.dto.OperationTypeDto;
import java.util.List;
import java.util.Optional;

/**
 * . Interface IoperationTypeService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
public interface IoperationTypeService {
  
  public OperationTypeDto createOperationType(OperationTypeDto operationType);
  
  public List<OperationTypeDto> getOperationTypes();
  
  public Optional<OperationTypeDto> getOperationType(Integer operationTypeId);
  
  public OperationTypeDto editOperationType(OperationTypeDto operationType);

}
