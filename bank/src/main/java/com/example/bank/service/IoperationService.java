package com.example.bank.service;

import com.example.bank.model.dto.OperationDto;
import java.util.Optional;

/**
 * . Interface IoperationService
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
public interface IoperationService {

  public OperationDto createOperation(OperationDto operationDto);
  
  public Optional<OperationDto> getMovements(Integer customerId);
  
  public Optional<OperationDto> getReportLastMovements(Integer customerId);

}
