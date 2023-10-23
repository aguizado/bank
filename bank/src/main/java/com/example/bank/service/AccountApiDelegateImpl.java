package com.example.bank.service;

import com.example.bank.model.AccountModel;
import com.example.bank.model.AccountModel.TypeAccountEnum;
import com.example.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class AccountApiDelegateImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
@Service
@RequiredArgsConstructor
public class AccountApiDelegateImpl implements AccountApiDelegate {
	
	private final AccountRepository accountRepository;
	
	@Override
	public AccountModel createAccount(AccountModel account) {
		validateAccount(account);
		return accountRepository.save(account);
	}
	
	public void validateAccount(AccountModel account) {
		if (TypeAccountEnum.SAVING.equals(account.getTypeAccount())) {
			setDataCustomer(account, false, 0, 3);
		}
		if (TypeAccountEnum.CURRENT.equals(account.getTypeAccount())) {
			setDataCustomer(account, true, 5, null);
		}
		if (TypeAccountEnum.FIXED_TERM.equals(account.getTypeAccount())) {
			setDataCustomer(account, false, 0, 1);
		}
	}
	
	public void setDataCustomer(AccountModel account, boolean mainFee, Integer mainValue, Integer transLimit) {
		account.setMaintenanceFee(mainFee);
		account.setMaintenanceValue(mainValue);
		account.setMonthlyTransactionLimit(transLimit);
	}

}
