package com.example.bank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.model.AccountModel;

/**.
* Class AccountRepository
*
* @author Andres Guizado
* @version 0.1, 2023/10/16
*/
@Repository
public interface AccountRepository extends MongoRepository<AccountModel, Integer>{

}
