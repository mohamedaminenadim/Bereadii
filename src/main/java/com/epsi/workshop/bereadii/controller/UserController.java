package com.epsi.workshop.bereadii.controller;

import com.epsi.workshop.bereadii.entity.UserEntity;
import com.epsi.workshop.bereadii.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/fullname")
    public UserEntity getUserByFullName(String clientFirstName, String clientFamilyName) {
        return userService.getUserByFullName(clientFirstName, clientFamilyName);
    }

    @PostMapping("/new")
    public ResponseEntity<UserEntity> createNewUser(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUserById(@PathVariable String id, @RequestBody UserEntity updatedUser) {
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
