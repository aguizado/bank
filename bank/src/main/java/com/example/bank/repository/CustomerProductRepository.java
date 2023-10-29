package com.example.bank.repository;

import com.example.bank.model.CustomerProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class CustomerProductRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Repository
public interface CustomerProductRepository extends MongoRepository<CustomerProductModel, Integer> {

}
