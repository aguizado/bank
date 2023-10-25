package com.example.bank.service;

import com.example.bank.model.LoanModel;
import java.util.Optional;

/**
 * . Interface LoanApiDelegate
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/25
 */
public interface LoanApiDelegate {
  
  public LoanModel createLoan(LoanModel loan);
  
  public Optional<LoanModel> getLoan(Integer loanId);
  
  public LoanModel editLoan(LoanModel loan);
  
  void deleteLoan(Integer loanId);

}
