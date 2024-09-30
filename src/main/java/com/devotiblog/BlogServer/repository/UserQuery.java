package com.devotiblog.BlogServer.repository;

import com.devotiblog.BlogServer.model.user.User;
import com.mongodb.client.result.DeleteResult;


public interface UserQuery {

    User updateUser(User user);
    DeleteResult removeUser(String userId);
}
