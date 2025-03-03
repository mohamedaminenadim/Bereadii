package com.epsi.workshop.bereadii.service;

import com.epsi.workshop.bereadii.controller.UserController;
import com.epsi.workshop.bereadii.entity.AppointmentEntity;
import com.epsi.workshop.bereadii.entity.UserEntity;
import com.epsi.workshop.bereadii.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserController userController;

    public List<AppointmentEntity> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public AppointmentEntity getAppointmentById(String id) {
        return appointmentRepository.findById(UUID.fromString(id)).orElseThrow();
    }

    public List<AppointmentEntity> getAppointmentsByClientFullName(String clientFirstName, String clientFamilyName) {
        UserEntity user = userController.getUserByFullName(clientFirstName, clientFamilyName);
        String id = user.getId();
        return appointmentRepository.findByClientId(id);
    }

    public AppointmentEntity createAppointment(AppointmentEntity appointment) {
        return appointmentRepository.save(appointment);
    }

    public AppointmentEntity updateAppointment(String id, AppointmentEntity updatedAppointment) {
         var isPresent = appointmentRepository.findById(UUID.fromString(id)).isPresent();
        if (isPresent) {
            AppointmentEntity existingAppointment = appointmentRepository.findById(UUID.fromString(id)).get();
            existingAppointment.setAppointmentDateTime(updatedAppointment.getAppointmentDateTime());
            existingAppointment.setClientId(updatedAppointment.getClientId());
            existingAppointment.setProfessionalId(updatedAppointment.getProfessionalId());
            existingAppointment.setServices(updatedAppointment.getServices());
            return appointmentRepository.save(existingAppointment);
        }
        throw new NoSuchElementException("Appointment not found");
    }

    public AppointmentEntity deleteAppointment(String id) {
        AppointmentEntity appointmentEntity = this.getAppointmentById(id);
        appointmentRepository.deleteById(UUID.fromString(id));
        return appointmentEntity;
    }
}
