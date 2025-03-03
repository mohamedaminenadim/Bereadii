package com.epsi.workshop.bereadii.repository;

import com.epsi.workshop.bereadii.entity.UserAuthenticationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthenticationRepository extends MongoRepository<UserAuthenticationEntity, UUID> {
    Optional<UserAuthenticationEntity> findByUsernameAndPassword(String username, String password);
    @Override
    UserAuthenticationEntity insert(UserAuthenticationEntity userAuthenticationEntity);
}
