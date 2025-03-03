package com.epsi.workshop.bereadii.service;

import com.epsi.workshop.bereadii.entity.UserEntity;
import com.epsi.workshop.bereadii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity getUserByFullName(String firstName, String lastName) {
        firstName = firstName.isBlank() || firstName.isEmpty() ? null : firstName;
        lastName = lastName.isBlank() || lastName.isEmpty() ? null : lastName;
        return userRepository.findByFirstNameAndFamilyName(firstName, lastName).orElse(new UserEntity());
    }

    public UserEntity createUser(UserEntity user) {

        return userRepository.save(user);
    }

    public UserEntity updateUser(String id, UserEntity updatedUser) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            var existingUser = userOpt.get();
            existingUser = new UserEntity(updatedUser);
            return userRepository.save(existingUser);
        }
        throw new NoSuchElementException("User not found, check if id exists");
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public LocalDateTime getDateTimeOfNow() {
        return LocalDateTime.now();
    }

    public UserEntity getUserByUsername(String username) {
        boolean isPresent = userRepository.findByUsername(username).isPresent();
        if (isPresent) {
            return userRepository.findByUsername(username).get();
        }
        return null;
    }
}
