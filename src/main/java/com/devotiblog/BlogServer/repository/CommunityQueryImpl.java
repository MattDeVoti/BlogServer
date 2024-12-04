package com.devotiblog.BlogServer.repository;

import com.devotiblog.BlogServer.model.community.Community;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class CommunityQueryImpl implements CommunityQuery{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CommunityQueryImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Community updateCommunity(Community community){
        Query query = new Query(where("communityId").is(community.getCommunityId()));
        Update update = new Update();
        update.set("communityName", community.getCommunityName());
        update.set("communityDescription", community.getCommunityDescription());
        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Community.class);
    }

    @Override
    public DeleteResult removeCommunity(String communityId){
        Query query = new Query(where("communityId").is(communityId));
        return mongoTemplate.remove(query, Community.class);
    }
}
