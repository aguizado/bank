package com.example.bank.service;

import com.example.bank.api.LoansApiDelegate;
import com.example.bank.model.Loan;
import com.example.bank.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**.
* Class LoanApiDelegateImpl

* @author Andres Guizado
* @version 0.1, 2023/10/16
*/
@Service
@RequiredArgsConstructor
public class LoanApiDelegateImpl implements LoansApiDelegate {
	
	private final LoanRepository loanRepository;
	
	@Override
	public ResponseEntity<Loan> loansPost(Loan loan) {
		loanRepository.save(loan);
		return ResponseEntity.ok(loan);
	}
	
	@Override
	public ResponseEntity<Loan> loansLoanIdGet(Integer loanId) {
		return loanRepository.findById(loanId).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@Override
	public ResponseEntity<Loan> loansLoanIdPut(Integer loanId, Loan loan) {
		loanRepository.save(loan);
		return ResponseEntity.ok(loan);
	}
	
	@Override
	public ResponseEntity<Void> loansLoanIdDelete(Integer loanId) {
		loanRepository.deleteById(loanId);
		return  ResponseEntity.ok().build();
	}

}
