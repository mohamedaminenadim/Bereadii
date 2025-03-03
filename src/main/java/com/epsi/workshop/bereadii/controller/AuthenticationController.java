package com.epsi.workshop.bereadii.controller;

import com.epsi.workshop.bereadii.entity.UserAuthenticationEntity;
import com.epsi.workshop.bereadii.entity.UserEntity;
import com.epsi.workshop.bereadii.service.AuthenticationService;
import com.epsi.workshop.bereadii.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/authentication")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserAuthenticationEntity register(@RequestParam String username, @RequestParam String password) throws SecurityException {
        UserAuthenticationEntity entity = authenticationService.registerUser(username, password);
        LOGGER.info("User registered with username {}.", username);
        return entity;
    }

    @PostMapping("/login")
    public UserEntity login(@RequestParam String username, @RequestParam String password) {
        try {
            UserAuthenticationEntity entity = authenticationService.loginUser(username, password);
            LOGGER.info("Login successful for user with username {}.", username);
            return userService.getUserByUsername(username);
        } catch (Exception exception) {
            LOGGER.error("Login failed. Cause :\n{}", exception.getMessage());
            throw new RuntimeException("Bad credentials");
        }
    }
}
