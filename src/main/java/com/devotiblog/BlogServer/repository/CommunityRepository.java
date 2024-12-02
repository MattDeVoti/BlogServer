package com.devotiblog.BlogServer.repository;

import com.devotiblog.BlogServer.model.community.Community;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommunityRepository extends MongoRepository<Community, String>, CommunityQuery{
    List<Community> findByCommunityId(String communityId);
    List<Community> findByCommunityName(String communityName);
}
