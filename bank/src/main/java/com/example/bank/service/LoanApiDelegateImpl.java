package com.example.bank.service;

import com.example.bank.model.LoanModel;
import com.example.bank.repository.LoanRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * . Class LoanApiDelegateImpl
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/25
 */
@Service
@RequiredArgsConstructor
public class LoanApiDelegateImpl implements LoanApiDelegate {
  
  private final LoanRepository loanRepository;

  @Override
  public LoanModel createLoan(LoanModel loan) {
    return loanRepository.save(loan);
  }

  @Override
  public Optional<LoanModel> getLoan(Integer loanId) {
    return loanRepository.findById(loanId);
  }

  @Override
  public LoanModel editLoan(LoanModel loan) {
    return createLoan(loan);
  }

  @Override
  public void deleteLoan(Integer loanId) {
    loanRepository.deleteById(loanId);
  }

}