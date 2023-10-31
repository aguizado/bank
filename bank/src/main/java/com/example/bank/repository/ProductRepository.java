package com.example.bank.repository;

import com.example.bank.model.ProductModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class ProductRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductModel, Integer> {

}
