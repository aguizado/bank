package com.example.bank.repository;

import com.example.bank.model.RepresentativeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class RepresentativeRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Repository
public interface RepresentativeRepository extends MongoRepository<RepresentativeModel, Integer> {

}
