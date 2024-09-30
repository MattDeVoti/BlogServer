package com.devotiblog.BlogServer.controller;

import com.devotiblog.BlogServer.model.post.Post;
import com.devotiblog.BlogServer.service.PostsService;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/devotiblog/posts")
public class PostController {

    private final PostsService postsService;

    @RequestMapping(method = RequestMethod.POST, value="createPost/{communityId}/{postId}/")
    @Operation(summary = "Create new post", description = "Creates a new post")
    public Post createNewPost(@PathVariable String communityId, @PathVariable String postId, @RequestBody Post request){
        return postsService.create(communityId, postId, request);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="deletePost/{postId}/")
    @Operation(summary = "Delete a post", description = "Deletes a post")
    public DeleteResult createNewPost(@PathVariable String postId){
        return postsService.delete(postId);
    }

    @RequestMapping(method = RequestMethod.PUT, value="updatePost/{postId}/")
    @Operation(summary = "Update a post", description = "Updates a post")
    public Post updatePost(@PathVariable String postId, @RequestBody Post request){
        return postsService.update(postId, request);
    }

    @RequestMapping(method = RequestMethod.GET, value="getPost/{postId}/")
    @Operation(summary = "Retrieve a post", description = "Retrieves a post")
    public Post getPost(@PathVariable String postId){
        return postsService.get(postId);
    }
}