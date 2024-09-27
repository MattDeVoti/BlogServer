package com.devotiblog.BlogServer.model.post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document(collection = "post")

public class Post {
    @Id
    String postId;

    String postBody;
    String posterId;
    String communityId;

    // Stores ids of users who have liked the post to keep track of who has already liked the post
    // Total likes equate to size of array
    String[] likedIds;

    // If the post is a response to another post, its parent is stored here
    Boolean isResponse;
    String parentPostId;
}
