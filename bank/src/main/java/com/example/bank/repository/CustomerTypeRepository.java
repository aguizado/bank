package com.example.bank.repository;

import com.example.bank.model.CustomerTypeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class CustomerTypeRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Repository
public interface CustomerTypeRepository extends MongoRepository<CustomerTypeModel, Integer> {

}
