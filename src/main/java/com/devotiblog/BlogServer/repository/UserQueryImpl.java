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

        update.set("userName", user.getUserName());
        update.set("email", user.getEmail());

        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), User.class);
    }

    @Override
    public DeleteResult removeUser(String userId){
        Query query = new Query(where("userId").is(userId));
        return mongoTemplate.remove(query, User.class);
    }

    @Override
    public User addUserToCommunity(String userId, String communityId, String communityRole){
        Query query = new Query(where("userId").is(userId));
        Update update = new Update();

        update.push(communityRole, communityId);

        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), User.class);
    }

    @Override
    public User removeUserFromCommunity(String userId, String communityId, String communityRole){
        Query query = new Query(where("userId").is(userId));
        Update update = new Update();

        update.pull(communityRole, communityId);

        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), User.class);
    }
}
