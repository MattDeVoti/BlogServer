package com.devotiblog.BlogServer.controller;

import com.devotiblog.BlogServer.model.user.User;
import com.devotiblog.BlogServer.service.UserService;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/devotiblog/users")

public class UserController {

    private final UserService userService;

    @RequestMapping(method = RequestMethod.POST, value="createUser/{userId}/")
    @Operation(summary = "Create new user", description = "Creates a new user")
    public User createNewUser(@PathVariable String userId, @RequestBody User request){
        return userService.create(userId, request);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="deleteUser/{userId}/")
    @Operation(summary = "Delete a user", description = "Deletes a user")
    public DeleteResult createNewUser(@PathVariable String userId){
        return userService.delete(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value="updateUser/{userId}/")
    @Operation(summary = "Update a user", description = "Updates a user")
    public User updateUser(@PathVariable String userId, @RequestBody User request){
        return userService.update(userId, request);
    }

    @RequestMapping(method = RequestMethod.GET, value="getUser/{userId}/")
    @Operation(summary = "Retrieve a user", description = "Retrieves a user")
    public User getUser(@PathVariable String userId){
        return userService.get(userId);
    }
}