package com.epsi.workshop.bereadii.service;

import com.epsi.workshop.bereadii.entity.UserEntity;
import com.epsi.workshop.bereadii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByFullName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndFamilyName(firstName, lastName).orElseThrow();
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity updateUser(String id, UserEntity updatedUser) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            UserEntity existingUser = userOpt.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setFamilyName(updatedUser.getFamilyName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            return userRepository.save(existingUser);
        }
        throw new RuntimeException("User not found, check if id exists");
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
