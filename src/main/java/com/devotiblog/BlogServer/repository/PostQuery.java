package com.devotiblog.BlogServer.repository;

import com.devotiblog.BlogServer.model.post.Post;
import com.mongodb.client.result.DeleteResult;

public interface PostQuery {
    Post updatePost(Post post);
    DeleteResult removePost(String postId);
}
