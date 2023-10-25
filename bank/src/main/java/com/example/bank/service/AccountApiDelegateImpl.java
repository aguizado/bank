package com.example.bank.service;

import com.example.bank.model.AccountModel;
import com.example.bank.model.AccountModel.TypeAccountEnum;
import com.example.bank.repository.AccountRepository;
import java.util.Optional;
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

  /**
   * . This method validate Account
   *
   * @param account This is the first parameter
   */
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

  /**
   * . This method set data Customer
   *
   * @param account This is the first parameter
   * @param mainFee This is the second parameter
   * @param mainValue This is the third parameter
   * @param transLimit This is the fourth parameter
   */
  public void setDataCustomer(AccountModel account, boolean mainFee,
      Integer mainValue, Integer transLimit) {
    account.setMaintenanceFee(mainFee);
    account.setMaintenanceValue(mainValue);
    account.setMonthlyTransactionLimit(transLimit);
  }

  @Override
  public Optional<AccountModel> getAccount(Integer accountId) {
    return accountRepository.findById(accountId);
  }

  @Override
  public AccountModel editAccount(AccountModel account) {
    return createAccount(account);
  }

  @Override
  public void deleteAccount(Integer accountId) {
    accountRepository.deleteById(accountId);
  }

}
