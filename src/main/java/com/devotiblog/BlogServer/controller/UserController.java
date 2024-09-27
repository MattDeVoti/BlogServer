package com.devotiblog.BlogServer.controller;

import com.devotiblog.BlogServer.model.post.Post;
import com.devotiblog.BlogServer.service.PostsService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/devotiblog/posts")
public class UserController {

    private final PostsService postsService;

    @RequestMapping(method = RequestMethod.POST, value="createPost/{communityId}/{postId}/")
    @Operation(summary = "Create new post", description = "Creates a new post")
    public Post createNewPost(@PathVariable String communityId, @PathVariable String postId, @RequestBody String postBody){
        return postsService.create(communityId, postId, postBody);
    }
}
