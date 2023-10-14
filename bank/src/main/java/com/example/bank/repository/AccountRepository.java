package com.example.bank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.model.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, Integer>{

}
