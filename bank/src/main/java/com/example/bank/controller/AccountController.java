package com.example.bank.controller;

import com.example.bank.model.AccountModel;
import com.example.bank.service.IAccountService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Class AccountController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/20
 */
@RestController
@RequiredArgsConstructor
public class AccountController {

  @Autowired
  IAccountService accountService;

  /**
   * . This method is to save Account
   *
   * @param account This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/accounts")
  public ResponseEntity<AccountModel> createAccount(@RequestBody AccountModel account) {
    try {
      AccountModel accountModel = accountService.createAccount(account);
      return new ResponseEntity<>(accountModel, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * . This method is to get Account
   *
   * @param accountId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/accounts/{accountId}")
  public ResponseEntity<AccountModel> getAccount(
      @PathVariable("accountId") Integer accountId) {
    Optional<AccountModel> opAccount = accountService.getAccount(accountId);
    if (opAccount.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opAccount.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  /**
   * . This method is to update Account
   *
   * @param accountId This is the first parameter
   * @param account   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/accounts/{accountId}")
  public ResponseEntity<AccountModel> editAccount(
      @PathVariable("accountId") Integer accountId,
      @RequestBody AccountModel account) {
    Optional<AccountModel> opAccount = accountService.getAccount(accountId);
    if (opAccount.isPresent()) {
      return new ResponseEntity<>(accountService.editAccount(account), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  /**
   * . This method is to delete Account
   *
   * @param accountId This is the first parameter
   * @return a HTTP Status
   */
  @DeleteMapping("/accounts/{accountId}")
  public ResponseEntity<Void> deleteAccount(
      @PathVariable("accountId") Integer accountId) {
    try {
      accountService.deleteAccount(accountId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
