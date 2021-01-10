package com.pclogo.demo.repository;

import com.pclogo.demo.entity.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<UserMongo, Integer> {
}
