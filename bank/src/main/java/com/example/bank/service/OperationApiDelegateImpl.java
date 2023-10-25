package com.example.bank.service;

import com.example.bank.model.AccountModel;
import com.example.bank.model.OperationModel;
import com.example.bank.model.OperationModel.TypeOperationEnum;
import com.example.bank.repository.OperationRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class OperationApiDelegateImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
@Service
@RequiredArgsConstructor
public class OperationApiDelegateImpl implements OperationApiDelegate {

  private final OperationRepository operationRepository;

  @Override
  public OperationModel createOperation(OperationModel operation) {
    Integer deposit = 0;
    Integer withdrawal = 0;
    if (TypeOperationEnum.DEPOSIT.equals(operation.getTypeOperation())) {
      deposit = operation.getBalance();
      updateAccount(operation, deposit, withdrawal);
    } else if (TypeOperationEnum.WITHDRAWAL.equals(operation.getTypeOperation())) {
      withdrawal = operation.getBalance();
      updateAccount(operation, deposit, withdrawal);
    }
    return operationRepository.save(operation);
  }

  private void updateAccount(OperationModel operation, Integer deposit, Integer withdrawal) {
    if (operation.getCustomer().getAccounts().isEmpty()) {
      // bad request
    } else {
      // Consultar lista de cuentas de cliente
      for (AccountModel account : operation.getCustomer().getAccounts()) {
        // comparar la cuenta de operation contra la lista
        Integer value = account.getAccountValue() + deposit - withdrawal;
        account.setAccountValue(value);
      }
    }
  }

  @Override
  public Optional<OperationModel> getOperation(Integer operationId) {
    return operationRepository.findById(operationId);
  }

}
