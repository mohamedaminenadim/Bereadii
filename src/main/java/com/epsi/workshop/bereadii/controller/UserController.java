package com.epsi.workshop.bereadii.controller;

import com.epsi.workshop.bereadii.entity.UserEntity;
import com.epsi.workshop.bereadii.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserService userService;

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all users",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No user found",
                    content = @Content) })
    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        try {
            List<UserEntity> users = userService.getAllUsers();
            LOGGER.info("Found {} users", users.size());
            return users;
        } catch (ResponseStatusException exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        } catch (NoSuchElementException exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        }
        return List.of(new UserEntity());
    }

    @GetMapping("/fullname")
    public UserEntity getUserByFullName(@RequestParam(name="firstName") String clientFirstName, @RequestParam(name="familyName") String clientFamilyName) {
        UserEntity user = userService.getUserByFullName(clientFirstName, clientFamilyName);
        String body = String.format("User with id '%s' has been found\n", user.getId());
        LOGGER.info(body);
        if (user.getId().isBlank() || user.getId().isEmpty()) throw new NoSuchElementException("User not found");
        return user;
    }

    @PostMapping("/new")
    public UserEntity createNewUser(@RequestBody UserEntity user) {
        try {
            var instant = userService.getDateTimeOfNow();
            UserEntity userEntity = new UserEntity(user);
            userService.createUser(userEntity);
            String body = String.format("User with id '%s' has been successfully created\n", userEntity.getId());
            LOGGER.info(body);
            return userEntity;
        } catch (Exception exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        }
        return null;
    }
    @PutMapping("/update/{id}")
    public UserEntity updateUserById(@PathVariable String id, @RequestBody UserEntity updatedUser) {
        UserEntity user = userService.getUserById(id);
        if (!user.getId().equals(id)){
            LOGGER.error("User not found");
            throw new NoSuchElementException("User not found");
        } else {
            LocalDateTime instant = userService.getDateTimeOfNow();
            updatedUser.setUpdatedAt(instant);
            UserEntity userEntity = userService.updateUser(id, updatedUser);
            String body = String.format("User with id '%s' has been successfully updated\n", id);
            LOGGER.info(body);
            return userEntity;
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable String id) throws NoSuchElementException {
        if (userService.getUserById(id) == null) {
            LOGGER.error("User with id {} not found", id);
            throw new NoSuchElementException("User not found");
        } else {
            userService.deleteUser(id);
            String body = String.format("User with id '%s' has been successfully deleted\n", id);
            LOGGER.info(body);
            return body;
        }
    }
}
