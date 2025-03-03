package com.epsi.workshop.bereadii.service;

import com.epsi.workshop.bereadii.entity.UserAuthenticationEntity;
import com.epsi.workshop.bereadii.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;


    public UserAuthenticationEntity registerUser(String username, String password) {
        String encodedPassword = this.encodePassword(password);
        UserAuthenticationEntity authenticationEntity = new UserAuthenticationEntity().toBuilder()
                .username(username)
                .password(encodedPassword)
                .build();
        return authenticationRepository.insert(authenticationEntity);
    }

    public UserAuthenticationEntity getUserByCredentials(String username, String password) {
        String encodedPassword = this.encodePassword(password);
        return authenticationRepository.findByUsernameAndPassword(username, encodedPassword).orElseThrow();
    }


    public UserAuthenticationEntity loginUser(String username, String password) {
        try {
            String encodedPassword = this.encodePassword(password);
            return getUserByCredentials(username, encodedPassword);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }


    private String encodePassword(String password) {
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        return encodedPassword;
    }
}
