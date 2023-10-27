package com.example.bank.service;

import com.example.bank.model.dto.AccountDto;
import java.util.Optional;

/**
 * . Interface CustomerApiDelegate
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
public interface IaccountService {

  public AccountDto createAccount(AccountDto account);
  
  public Optional<AccountDto> getAccount(Integer accountId);
  
  public AccountDto editAccount(AccountDto account);
  
  void deleteAccount(Integer accountId);
  
}
