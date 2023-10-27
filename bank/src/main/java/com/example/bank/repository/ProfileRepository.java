package com.example.bank.repository;

import com.example.bank.model.ProfileModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class ProfileRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Repository
public interface ProfileRepository extends MongoRepository<ProfileModel, Integer> {

}
