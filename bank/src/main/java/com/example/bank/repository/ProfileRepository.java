package com.example.bank.repository;

import com.example.bank.model.ProfileModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * . Class ProfileRepository
 *
 * @author Andres Guizado
 * @version 0.1, 2023/10/26
 */
@Repository
public interface ProfileRepository extends ReactiveMongoRepository<ProfileModel, Integer> {

}
