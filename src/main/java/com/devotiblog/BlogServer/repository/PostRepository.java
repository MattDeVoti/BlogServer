package com.devotiblog.BlogServer.repository;

import com.devotiblog.BlogServer.model.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String>, PostQuery{
    List<Post> findByPostId(String postId);
}