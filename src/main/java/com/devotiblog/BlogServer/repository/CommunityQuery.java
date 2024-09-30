package com.devotiblog.BlogServer.repository;

import com.devotiblog.BlogServer.model.community.Community;
import com.mongodb.client.result.DeleteResult;

public interface CommunityQuery {
    Community updateCommunity(Community community);
    DeleteResult removeCommunity(String communityId);
}
