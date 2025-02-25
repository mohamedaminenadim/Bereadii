package com.epsi.workshop.bereadii.repository;

import com.epsi.workshop.bereadii.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    @Override
    List<UserEntity> findAll();
    Optional<UserEntity> findByFirstNameAndFamilyName(String firstName, String lastName);
    UserEntity findByEmail(String email);
}
