package com.devotiblog.BlogServer.model.community;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document(collection = "community")
public class Community {
    @Id
    String communityId;

    String communityName;
    String communityDescription;
}
