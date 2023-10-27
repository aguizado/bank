package com.example.bank.service.impl;

import com.example.bank.model.AccountModel;
import com.example.bank.model.dto.AccountDto;
import com.example.bank.model.dto.AccountDto.TypeAccountEnumDto;
import com.example.bank.model.mapper.AccountMapper;
import com.example.bank.repository.AccountRepository;
import com.example.bank.service.IaccountService;
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
public class AccountServiceImpl implements IaccountService {

  private final AccountRepository accountRepository;
  
  private final AccountMapper accountMapper;

  @Override
  public AccountDto createAccount(AccountDto account) {
    validateAccount(account);    
    AccountModel accountModel = accountMapper.toAccount(account);    
    return accountMapper.INSTANCE.toEntity(accountRepository.save(accountModel));
  }

  /**
   * . This method validate Account
   *
   * @param account This is the first parameter
   */
  public void validateAccount(AccountDto account) {
    if (TypeAccountEnumDto.SAVING.equals(account.getTypeAccount())) {
      setDataCustomer(account, false, 0, 3);
    }
    if (TypeAccountEnumDto.CURRENT.equals(account.getTypeAccount())) {
      setDataCustomer(account, true, 5, null);
    }
    if (TypeAccountEnumDto.FIXED_TERM.equals(account.getTypeAccount())) {
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
  public void setDataCustomer(AccountDto account, boolean mainFee,
      Integer mainValue, Integer transLimit) {
    account.setMaintenanceFee(mainFee);
    account.setMaintenanceValue(mainValue);
    account.setMonthlyTransactionLimit(transLimit);
  }

  @Override
  public Optional<AccountDto> getAccount(Integer accountId) {
    return Optional.of(accountMapper.toEntity(accountRepository.findById(accountId).get()));
  }

  @Override
  public AccountDto editAccount(AccountDto account) {
    return createAccount(account);
  }

  @Override
  public void deleteAccount(Integer accountId) {
    accountRepository.deleteById(accountId);
  }

}
