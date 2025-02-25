package com.epsi.workshop.bereadii.repository;

import com.epsi.workshop.bereadii.entity.AppointmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends MongoRepository<AppointmentEntity, String> {
    Optional<AppointmentEntity> findById(String id);
    List<AppointmentEntity> findByClientId(String id);
}
