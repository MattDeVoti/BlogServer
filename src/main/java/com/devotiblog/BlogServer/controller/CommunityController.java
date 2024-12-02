package com.devotiblog.BlogServer.controller;

import com.devotiblog.BlogServer.model.community.Community;
import com.devotiblog.BlogServer.service.CommunityService;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/devotiblog/communities")

public class CommunityController {

    private final CommunityService communityService;

    @RequestMapping(method = RequestMethod.POST, value="createUser/{communityId}")
    @Operation(summary = "Create new community", description = "Creates a new community")
    public Community createNewCommunity(@PathVariable String communityId, @RequestBody Community request){
        return communityService.create(communityId, request);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="deleteUser/{communityId}")
    @Operation(summary = "Delete a community", description = "Deletes a community")
    public DeleteResult createNewCommunity(@PathVariable String communityId){
        return communityService.delete(communityId);
    }

    @RequestMapping(method = RequestMethod.PUT, value="updateUser/{communityId}")
    @Operation(summary = "Update a community", description = "Updates a community")
    public Community updateCommunity(@PathVariable String communityId, @RequestBody Community request){
        return communityService.update(communityId, request);
    }

    @RequestMapping(method = RequestMethod.GET, value="getUser/{communityId}")
    @Operation(summary = "Retrieve a community", description = "Retrieves a community")
    public Community getCommunity(@PathVariable String communityId){
        return communityService.get(communityId);
    }
}