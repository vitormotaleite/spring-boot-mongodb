package com.vitor.spring.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vitor.spring.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>{

}
