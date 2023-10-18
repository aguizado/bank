package com.example.bank.repository;

import com.example.bank.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**.
* Class AccountRepository

* @author Andres Guizado
* @version 0.1, 2023/10/16
*/
@Repository
public interface AccountRepository extends MongoRepository<Account, Integer> {

}
