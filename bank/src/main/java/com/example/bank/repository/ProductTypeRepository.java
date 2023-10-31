package com.example.bank.repository;

import com.example.bank.model.ProductTypeModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class ProductTypeRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Repository
public interface ProductTypeRepository extends ReactiveMongoRepository<ProductTypeModel, Integer> {

}
