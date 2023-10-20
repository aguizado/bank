package com.example.bank.repository;

import com.example.bank.model.LoanModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**.
* Class CustomerRepository

* @author Andres Guizado
* @version 0.1, 2023/10/16
*/
@Repository
public interface LoanRepository extends MongoRepository<LoanModel, Integer> {

}
