package com.example.bank.repository;

import com.example.bank.model.TransferModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class TransferRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/11/05
 */
@Repository
public interface TransferRepository extends
    ReactiveMongoRepository<TransferModel, Integer> {

}
