package com.devotiblog.BlogServer.service;

import com.devotiblog.BlogServer.exception.BadRequestException;
import com.devotiblog.BlogServer.model.post.Post;
import com.devotiblog.BlogServer.repository.PostRepository;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostsService {
    private final PostRepository postRepository;

    public Post create(String communityId, String postId, Post request){
        Post post = validation(communityId, postId, request);

        // Saves new post to MongoDB
        post = postRepository.save(post);

        log.debug("Creating post with postId {} in community {}", post.getPostId(), post.getCommunityId());
        return post;
    }

    public DeleteResult delete(String postId){
        validation(postId);

        DeleteResult result = postRepository.removePost(postId);
        log.debug("Post deleted successfully : {}", result.wasAcknowledged());
        return result;
    }

    public Post update(String postId, Post request){
        Post post = validation(postId, request);

        post = postRepository.updatePost(request);
        log.debug("Updating post {}", post.getPostId());
        return post;
    }

    public Post get(String postId){
        validation(postId);

        // Retrieves post by ID
        Post post = postRepository.findByPostId(postId).get(0);
        log.debug("Retrieving post {}", post.getPostId());
        return post;
    }

    public Post addLike(String postId, String userId){
        validation(postId, userId);

        Post post = postRepository.findByPostId(postId).get(0);
        if(!Arrays.asList(post.getLikedIds()).contains(userId)) {
            post = postRepository.addLikeOnPost(postId, userId);
        } else {
            throw new BadRequestException("User " + userId + " liked post " + postId);
        }
        log.debug("User {} liked post {}", userId, postId);
        return post;
    }

    public Post removeLike(String postId, String userId){
        validation(postId, userId);

        Post post = postRepository.removeLikeOnPost(postId, userId);
        log.debug("User {} unliked post {}", userId, postId);
        return post;
    }

    public Post validation(String communityId, String postId, Post request){
        if(communityId.isBlank()){
            throw new BadRequestException("communityId in post validation cannot be null");
        }
        if(postId.isBlank()){
            throw new BadRequestException("postId cannot be null");
        }
        if(request == null){
            throw new BadRequestException("postBody cannot be null");
        }
        request.setPostId(postId);
        request.setCommunityId(communityId);
        return request;
    }

    public Post validation(String postId, Post request){
        if(postId.isBlank()){
            throw new BadRequestException("postId cannot be null");
        }
        if(request == null){
            throw new BadRequestException("postBody cannot be null");
        }
        request.setPostId(postId);
        return request;
    }

    public void validation(String postId){
        if(postId.isBlank()){
            throw new BadRequestException("postId cannot be null");
        }
    }

    public void validation(String postId, String userId){
        if(postId.isBlank()){
            throw new BadRequestException("postId cannot be null");
        }
        if(userId.isBlank()){
            throw new BadRequestException("userId cannot be null");
        }
    }
}