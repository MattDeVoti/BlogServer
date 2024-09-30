package com.devotiblog.BlogServer.repository;

import com.devotiblog.BlogServer.model.user.User;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class UserQueryImpl implements UserQuery{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserQueryImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User updateUser(User user){
        Query query = new Query(where("userId").is(user.getUserId()));
        Update update = new Update();
        update.set("userId", user.getUserId());
        update.set("email", user.getEmail());
        // TODO: Add new community
        // TODO: Add new moderator of community
        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), User.class);
    }

    @Override
    public DeleteResult removeUser(String userId){
        Query query = new Query(where("userId").is(userId));
        return mongoTemplate.remove(query, User.class);
    }
}
