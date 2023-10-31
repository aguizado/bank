package com.example.bank.repository;

import com.example.bank.model.OperationModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class OperationRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/16
 */
@Repository
public interface OperationRepository extends ReactiveMongoRepository<OperationModel, Integer> {

}
