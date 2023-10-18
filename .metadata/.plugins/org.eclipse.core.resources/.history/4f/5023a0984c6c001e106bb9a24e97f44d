package com.example.bank.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.api.TransactionsApiDelegate;
import com.example.bank.model.Account;
import com.example.bank.model.Operation;
import com.example.bank.model.OperationType.DescriptionEnum;
import com.example.bank.repository.OperationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperationApiDelegateImpl implements TransactionsApiDelegate{
	
	private final OperationRepository operationRepository;
	
	@Override
	public ResponseEntity<Operation> transactionsPost(Operation operation) {
		
		Integer deposit = 0;
		Integer withdrawal = 0;
		
		if (DescriptionEnum.DEPOSIT.equals(operation.getTypeOperation().getDescription())) {
			
			deposit = operation.getBalance();			
			updateAccount(operation, deposit, withdrawal);
			
		} else if(DescriptionEnum.WITHDRAWAL.equals(operation.getTypeOperation().getDescription())) {
			
			withdrawal = operation.getBalance();			
			updateAccount(operation, deposit, withdrawal);
		}
		
		operationRepository.save(operation);
		return ResponseEntity.ok(operation);
	}

	private void updateAccount(Operation operation, Integer deposit, Integer withdrawal) {
		if (operation.getCustomer().getAccounts().size() == 0) {
			//bad request
		} else {
			//Consultar lista de cuentas de cliente
			for (Account account : operation.getCustomer().getAccounts()) {					
				//comparar la cuenta de operation contra la lista
				Integer value = account.getAccountValue() + deposit - withdrawal;
				account.setAccountValue(value);
			}
		}
	}
	
	@Override
	public ResponseEntity<Operation> transactionsCustomerIdGet(Integer customerId){		
		return operationRepository.findById(customerId).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

}
