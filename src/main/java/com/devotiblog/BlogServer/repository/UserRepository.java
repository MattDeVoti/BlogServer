package com.devotiblog.BlogServer.repository;

import com.devotiblog.BlogServer.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String>, UserQuery{
    List<User> findByUserId(String userId);
    List<User> findByUserName(String userName);
}
