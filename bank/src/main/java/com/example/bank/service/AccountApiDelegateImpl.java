package com.example.bank.service;

import com.example.bank.api.AccountsApiDelegate;
import com.example.bank.model.Account;
import com.example.bank.model.AccountType.DescriptionEnum;
import com.example.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**.
* Class AccountApiDelegateImpl

* @author Andres Guizado
* @version 0.1, 2023/10/16
*/
@Service
@RequiredArgsConstructor
public class AccountApiDelegateImpl implements AccountsApiDelegate {
	
	private final AccountRepository accountRepository;
	private Integer monthlyTransactionLimitSaving = 3;
	private Integer monthlyTransactionLimitFixedTerm = 1;
	private Integer maintenanceValue = 5;
	private Integer zero = 0;

	@Override
	public ResponseEntity<Account> accountsPost(Account account) {

		if(DescriptionEnum.SAVING.equals(account.getType().getDescription())) {
			account.setMaintenanceFee(false);
			account.setMaintenanceValue(zero);
			account.setMonthlyTransactionLimit(monthlyTransactionLimitSaving);
		} else if (DescriptionEnum.CURRENT.equals(account.getType().getDescription())) {
			account.setMaintenanceFee(true);
			account.setMaintenanceValue(maintenanceValue);
			account.setMonthlyTransactionLimit(null);
		} else if (DescriptionEnum.FIXED_TERM.equals(account.getType().getDescription())) {
			account.setMaintenanceFee(false);
			account.setMaintenanceValue(zero);
			account.setMonthlyTransactionLimit(monthlyTransactionLimitFixedTerm);
		}

		accountRepository.save(account);
		return ResponseEntity.ok(account);
	}
	
	@Override
	public ResponseEntity<Account> accountsAccountIdGet(Integer accountId) {		
		return accountRepository.findById(accountId).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@Override
	public ResponseEntity<Account> accountsAccountIdPut(Integer accountId, Account account) {
		accountRepository.save(account);
		return ResponseEntity.ok(account);
	}
	
	@Override
	public ResponseEntity<Void> accountsAccountIdDelete(Integer accountId) {
		accountRepository.deleteById(accountId);
		return  ResponseEntity.ok().build();
	}

}
