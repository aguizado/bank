package com.example.bank.repository;

import com.example.bank.model.CustomerProductModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * . Class CustomerProductRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Repository
public interface CustomerProductRepository extends 
    ReactiveMongoRepository<CustomerProductModel, Integer> {
  
  Flux<CustomerProductModel> findByCustomerId(Integer customerId);

}
