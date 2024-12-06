package com.devotiblog.BlogServer.repository;

import com.devotiblog.BlogServer.model.post.Post;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class PostQueryImpl implements PostQuery{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public PostQueryImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Post updatePost(Post post){
        Query query = new Query(where("postId").is(post.getPostId()));
        Update update = new Update();
        update.set("postBody", post.getPostBody());
        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Post.class);
    }

    @Override
    public Post addLikeOnPost(String postId, String likedId){
        Query query = new Query(where("postId").is(postId));
        Update update = new Update();
        update.push("likedIds", likedId);
        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Post.class);
    }

    @Override
    public Post removeLikeOnPost(String postId, String likedId){
        Query query = new Query(where("postId").is(postId));
        Update update = new Update();
        update.pull("likedIds", likedId);
        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Post.class);
    }

    @Override
    public DeleteResult removePost(String postId){
        Query query = new Query(where("postId").is(postId));
        return mongoTemplate.remove(query, Post.class);
    }
}
