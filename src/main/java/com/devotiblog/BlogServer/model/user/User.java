package com.devotiblog.BlogServer.model.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document(collection = "user")
public class User {

    @Id
    String userId;

    String userName;
    String email;

    // Ids of all communities that this user is a member of
    String[] memberOfCommunityIds;

    // Ids of all communities that this user has created
    String[] moderatorOfCommunityIds;
}