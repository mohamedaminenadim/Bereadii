package com.epsi.workshop.bereadii.service;

import com.epsi.workshop.bereadii.controller.UserController;
import com.epsi.workshop.bereadii.entity.AppointmentEntity;
import com.epsi.workshop.bereadii.entity.UserEntity;
import com.epsi.workshop.bereadii.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<AppointmentEntity> getAppointmentsByClientFullName(String clientFirstName, String clientFamilyName) {
        UserEntity user = userController.getUserByFullName(clientFirstName, clientFamilyName);
        String id = user.getId();
        return appointmentRepository.findByClientId(id);
    }

    public AppointmentEntity createAppointment(AppointmentEntity appointment) {
        return appointmentRepository.save(appointment);
    }

    public AppointmentEntity updateAppointment(String id, AppointmentEntity updatedAppointment) {
        Optional<AppointmentEntity> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            AppointmentEntity existingAppointment = appointmentOpt.get();
            existingAppointment.setAppointmentDateTime(updatedAppointment.getAppointmentDateTime());
            existingAppointment.setClientId(updatedAppointment.getClientId());
            existingAppointment.setProfessionalId(updatedAppointment.getProfessionalId());
            existingAppointment.setServices(updatedAppointment.getServices());
            return appointmentRepository.save(existingAppointment);
        }
        throw new RuntimeException("Appointment not found");
    }

    public AppointmentEntity deleteAppointment(String id) {
        AppointmentEntity appointmentEntity = appointmentRepository.findById(id).get();
        appointmentRepository.deleteById(id);
        return appointmentEntity;
    }
}
