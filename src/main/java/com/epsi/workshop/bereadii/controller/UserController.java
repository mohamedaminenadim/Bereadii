package com.epsi.workshop.bereadii.controller;

import com.epsi.workshop.bereadii.entity.AppointmentEntity;
import com.epsi.workshop.bereadii.entity.UserEntity;
import com.epsi.workshop.bereadii.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserService userService;

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/fullname")
    public ResponseEntity<UserEntity> getUserByFullName(String clientFirstName, String clientFamilyName) {
        try {
            UserEntity user = userService.getUserByFullName(clientFirstName, clientFamilyName);
            String body = String.format("User with id '%s' has been found\n", user.getId());
            LOGGER.info(body);
            return ResponseEntity.status(200).body(user);
        } catch (Exception exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/new")
    public ResponseEntity<UserEntity> createNewUser(@RequestBody UserEntity user) {
        try {
            UserEntity userEntity = userService.createUser(user);
            String body = String.format("User with id '%s' has been successfully created\n", userEntity.getId());
            LOGGER.info(body);
            return ResponseEntity.status(201).body(userEntity);
        } catch (Exception exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        }
        return ResponseEntity.status(400).build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUserById(@PathVariable String id, @RequestBody UserEntity updatedUser) {
        try {
            UserEntity userEntity = userService.updateUser(id, updatedUser);
            String body = String.format("User with id '%s' has been successfully updated\n", id);
            LOGGER.info(body);
            return ResponseEntity.status(200).body(userEntity);
        } catch (Exception exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        }
        return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        try {
            UserEntity userEntity = userService.deleteUser(id);
            String body = String.format("User with id '%s' has been successfully deleted\n", id);
            LOGGER.info(body);
            return ResponseEntity.status(200).body(body + userEntity);
        } catch (Exception exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        }
        return ResponseEntity.status(400).build();
    }
}
