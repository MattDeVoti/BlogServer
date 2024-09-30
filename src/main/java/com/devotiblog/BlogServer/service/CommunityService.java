package com.devotiblog.BlogServer.service;

import com.devotiblog.BlogServer.exception.BadRequestException;
import com.devotiblog.BlogServer.model.community.Community;
import com.devotiblog.BlogServer.repository.CommunityRepository;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    public Community create(String communityId, Community request){
        Community community = validation(communityId, request);

        // Saves new community to MongoDB
        community = communityRepository.save(community);

        log.debug("Creating community {} with communityId {}", community.getCommunityName(), community.getCommunityId());
        return community;
    }

    public DeleteResult delete(String communityId){
        validation(communityId);

        DeleteResult result = communityRepository.removeCommunity(communityId);
        log.debug("Community deleted successfully : {}", result.wasAcknowledged());
        return result;
    }

    public Community update(String communityId, Community request){
        Community community = validation(communityId, request);

        community = communityRepository.updateCommunity(request);
        log.debug("Updating community {} with communityId {}", community.getCommunityName(), community.getCommunityId());
        return community;
    }

    public Community get(String communityId){
        validation(communityId);

        // Retrieves community by ID
        Community community = communityRepository.findByCommunityId(communityId).get(0);
        log.debug("Retrieving community {} with communityId {}", community.getCommunityName(), community.getCommunityId());
        return community;
    }

    public Community validation(String communityId, Community request){
        if(communityId.isBlank()){
            throw new BadRequestException("communityId cannot be null");
        }
        if(request == null){
            throw new BadRequestException("communityBody cannot be null");
        }
        request.setCommunityId(communityId);
        return request;
    }

    public void validation(String communityId){
        if(communityId.isBlank()){
            throw new BadRequestException("communityId cannot be null");
        }
    }
}
