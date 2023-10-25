package com.example.bank.service;

import com.example.bank.model.OperationModel;
import java.util.Optional;

/**
 * . Interface OperationApiDelegate
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
public interface IOperationService {

  public OperationModel createOperation(OperationModel operation);
  
  public Optional<OperationModel> getOperation(Integer operationId);

}
