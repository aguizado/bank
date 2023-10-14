package com.example.bank.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.api.TransactionsApiDelegate;
import com.example.bank.model.Operation;
import com.example.bank.repository.OperationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperationApiDelegateImpl implements TransactionsApiDelegate{
	
	private final OperationRepository operationRepository;
	
	@Override
	public ResponseEntity<Operation> transactionsPost(Operation operation) {
		operationRepository.save(operation);
		return ResponseEntity.ok(operation);
	}
	
	@Override
	public ResponseEntity<Operation> transactionsCustomerIdGet(Integer customerId){		
		return operationRepository.findById(customerId).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

}
