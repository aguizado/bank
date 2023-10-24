package com.example.bank.controller;

import com.example.bank.model.LoanModel;
import com.example.bank.repository.LoanRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
 * . Class LoanController
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/20
 */
@RestController
@RequiredArgsConstructor
public class LoanController {

  private final LoanRepository loanRepository;

  /**
   * . This method is to create Loan
   *
   * @param loan This is the first parameter
   * @return a HTTP Status
   */
  @PostMapping("/loans")
  public ResponseEntity<LoanModel> loansPost(@RequestBody LoanModel loan) {
    try {
      LoanModel loanModel = loanRepository.save(loan);
      return new ResponseEntity<>(loanModel, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * . This method is to get Loan
   *
   * @param loanId This is the first parameter
   * @return a HTTP Status
   */
  @GetMapping("/loans/{loanId}")
  public ResponseEntity<LoanModel> loansLoanIdGet(@PathVariable("loanId") Integer loanId) {
    Optional<LoanModel> opLoan = loanRepository.findById(loanId);
    if (opLoan.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(opLoan.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  /**
   * . This method is to update Loan
   *
   * @param loanId This is the first parameter
   * @param loan   This is the second parameter
   * @return a HTTP Status
   */
  @PutMapping("/loans/{loanId}")
  public ResponseEntity<LoanModel> loansLoanIdPut(
      @PathVariable("loanId") Integer loanId, @RequestBody LoanModel loan) {
    Optional<LoanModel> opLoan = loanRepository.findById(loanId);
    if (opLoan.isPresent()) {
      return new ResponseEntity<>(loanRepository.save(loan), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  /**
   * . This method is to delete Loan
   *
   * @param loanId This is the first parameter
   * @return a HTTP Status
   */
  @DeleteMapping("/loans/{loanId}")
  public ResponseEntity<Void> loansLoanIdDelete(@PathVariable("loanId") Integer loanId) {
    try {
      loanRepository.deleteById(loanId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
