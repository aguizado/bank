package com.example.bank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.model.Operation;

@Repository
public interface OperationRepository extends MongoRepository<Operation, Integer> {

}
