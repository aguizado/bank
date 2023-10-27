package com.example.bank.repository;

import com.example.bank.model.CardModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class CardRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Repository
public interface CardRepository extends MongoRepository<CardModel, Integer> {

}
