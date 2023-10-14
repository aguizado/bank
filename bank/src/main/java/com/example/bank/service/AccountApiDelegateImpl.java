package com.example.bank.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.api.AccountsApiDelegate;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountApiDelegateImpl implements AccountsApiDelegate{
	
	private final AccountRepository accountRepository;
	
	@Override
	public ResponseEntity<Account> accountsPost(Account account) {
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