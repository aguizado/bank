package com.example.bank.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.api.AccountsApiDelegate;
import com.example.bank.model.Account;
import com.example.bank.model.AccountType.DescriptionEnum;
import com.example.bank.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountApiDelegateImpl implements AccountsApiDelegate{
	
	private final AccountRepository accountRepository;
	private Integer MONTHLY_TRANSACTION_LIMIT_SAVING = 3;
	private Integer MONTHLY_TRANSACTION_LIMIT_FIXED_TERM = 1;
	private Integer MAINTENANCE_VALUE = 5;
	private Integer ZERO = 0;
	
	@Override
	public ResponseEntity<Account> accountsPost(Account account) {
		
		if(DescriptionEnum.SAVING.equals(account.getType().getDescription())) {
			account.setMaintenanceFee(false);
			account.setMaintenanceValue(ZERO);
			account.setMonthlyTransactionLimit(MONTHLY_TRANSACTION_LIMIT_SAVING);
		} else if (DescriptionEnum.CURRENT.equals(account.getType().getDescription())) {
			account.setMaintenanceFee(true);
			account.setMaintenanceValue(MAINTENANCE_VALUE);
			account.setMonthlyTransactionLimit(null);
		} else if (DescriptionEnum.FIXED_TERM.equals(account.getType().getDescription())) {
			account.setMaintenanceFee(false);
			account.setMaintenanceValue(ZERO);
			account.setMonthlyTransactionLimit(MONTHLY_TRANSACTION_LIMIT_FIXED_TERM);
		}
		
		accountRepository.save(account);
		return ResponseEntity.ok(account);
	}
	
	@Override
	public ResponseEntity<Account> accountsAccountIdGet(Integer accountId){		
		return accountRepository.findById(accountId).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@Override
	public ResponseEntity<Account> accountsAccountIdPut(Integer accountId, Account account) {
		accountRepository.save(account);
		return ResponseEntity.ok(account);
	}
	
	@Override
	public ResponseEntity<Void> accountsAccountIdDelete(Integer accountId){
		accountRepository.deleteById(accountId);
		return  ResponseEntity.ok().build();
	}

}
