package com.devotiblog.BlogServer.service;

import com.devotiblog.BlogServer.model.post.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostsService {

    public Post create(String communityId, String postId, String postBody){
        Post post = new Post(); // TODO: Build post properly later
        // TODO: more logic here
        return post;
    }


}
