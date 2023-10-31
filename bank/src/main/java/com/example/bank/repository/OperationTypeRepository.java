package com.example.bank.repository;

import com.example.bank.model.OperationTypeModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class OperationTypeRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Repository
public interface OperationTypeRepository extends
    ReactiveMongoRepository<OperationTypeModel, Integer> {

}
