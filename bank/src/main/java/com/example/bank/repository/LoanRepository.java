package com.example.bank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.model.Loan;

@Repository
public interface LoanRepository extends MongoRepository<Loan, Integer>{

}
