package com.example.bank.repository;

import com.example.bank.model.RepresentativeTypeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class RepresentativeTypeRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/27
 */
@Repository
public interface RepresentativeTypeRepository extends 
    MongoRepository<RepresentativeTypeModel, Integer> {

}
