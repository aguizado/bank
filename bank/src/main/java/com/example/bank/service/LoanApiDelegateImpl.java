package com.example.bank.service;

import com.example.bank.api.LoansApiDelegate;
import com.example.bank.mapper.LoanMapper;
import com.example.bank.model.Loan;
import com.example.bank.model.LoanModel;
import com.example.bank.repository.LoanRepository;

import java.util.Optional;

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
	
	private final LoanMapper loanMapper;
	
//	@Override
//	public ResponseEntity<Loan> loansPost(Loan loan) {
//		loanRepository.save(loan);
//		return ResponseEntity.ok(loan);
//	}
	
	@Override
	public ResponseEntity<Loan> loansLoanIdGet(Integer loanId) {
		Optional<LoanModel> loan = loanRepository.findById(loanId);
		if(loan.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(loanMapper.toLoan(loan.get()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
//	@Override
//	public ResponseEntity<Loan> loansLoanIdPut(Integer loanId, Loan loan) {
//		loanRepository.save(loan);
//		return ResponseEntity.ok(loan);
//	}
	
	@Override
	public ResponseEntity<Void> loansLoanIdDelete(Integer loanId) {
		loanRepository.deleteById(loanId);
		return  ResponseEntity.ok().build();
	}

}
