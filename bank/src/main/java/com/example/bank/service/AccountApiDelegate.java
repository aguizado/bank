package com.example.bank.service;

import com.example.bank.model.AccountModel;
import java.util.Optional;

/**
 * . Interface CustomerApiDelegate
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
public interface AccountApiDelegate {

  public AccountModel createAccount(AccountModel account);
  
  public Optional<AccountModel> getAccount(Integer accountId);
  
  public AccountModel editAccount(AccountModel account);
  
  void deleteAccount(Integer accountId);
  
}
