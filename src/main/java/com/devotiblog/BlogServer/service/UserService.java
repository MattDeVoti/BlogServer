package com.devotiblog.BlogServer.service;

import com.devotiblog.BlogServer.exception.BadRequestException;
import com.devotiblog.BlogServer.model.user.User;
import com.devotiblog.BlogServer.repository.UserRepository;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public User create(String userId, User request){
        User user = validation(userId, request);

        // Saves new user to MongoDB
        user = userRepository.save(user);

        log.debug("Creating user {} with userId {}", user.getUserName(), user.getUserId());
        return user;
    }

    public DeleteResult delete(String userId){
        validation(userId);

        DeleteResult result = userRepository.removeUser(userId);
        log.debug("User deleted successfully : {}", result.wasAcknowledged());
        return result;
    }

    public User update(String userId, User request){
        User user = validation(userId, request);

        user = userRepository.updateUser(request);
        log.debug("Updating user {} with userId {}", user.getUserName(), user.getUserId());
        return user;
    }

    public User get(String userId){
        validation(userId);

        // Retrieves user by ID
        User user = userRepository.findByUserId(userId).get(0);
        log.debug("Retrieving user {} with userId {}", user.getUserName(), user.getUserId());
        return user;
    }

    public User addToCommunity(String userId, String communityId, boolean asMod){
        validation(userId, communityId);

        String communityField = asMod ? "moderatorOfCommunityIds" : "memberOfCommunityIds";

        User user = userRepository.addUserToCommunity(userId, communityId, communityField);
        log.debug("Added user with userId {} to communityId {}", user.getUserId(), communityId);
        return user;
    }

    public User removeFromCommunity(String userId, String communityId, boolean asMod){
        validation(userId, communityId);

        String communityField = asMod ? "moderatorOfCommunityIds" : "memberOfCommunityIds";

        User user = userRepository.removeUserFromCommunity(userId, communityId, communityField);
        log.debug("Removed user with userId {} from communityId {}", user.getUserId(), communityId);
        return user;
    }

    public void validation(String userId, String communityId){
        if(userId.isBlank()){
            throw new BadRequestException("userId cannot be null");
        }
        if(communityId.isBlank()){
            throw new BadRequestException("communityId in UserService cannot be null");
        }
    }

    public User validation(String userId, User request){
        if(userId.isBlank()){
            throw new BadRequestException("userId cannot be null");
        }
        if(request == null){
            throw new BadRequestException("userBody cannot be null");
        }
        request.setUserId(userId);
        return request;
    }

    public void validation(String userId){
        if(userId.isBlank()){
            throw new BadRequestException("userId cannot be null");
        }
    }
}