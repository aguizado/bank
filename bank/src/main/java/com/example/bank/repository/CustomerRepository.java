package com.example.bank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer>{

}
