package com.epsi.workshop.bereadii.controller;

import com.epsi.workshop.bereadii.entity.AppointmentEntity;
import com.epsi.workshop.bereadii.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/appointment/")
@Slf4j
public class AppointmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    public AppointmentService appointmentService;

    @GetMapping("/all")
    public List<AppointmentEntity> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentEntity getAppointmentById(@PathVariable String id) {
        if (appointmentService.getAppointmentById(id) != null){
            LOGGER.info("Found appointment with id '{}'", id);
            return appointmentService.getAppointmentById(id);
        } else {
            LOGGER.error("No element found with id '{}'", id);
            return null;
        }
    }

    @GetMapping("/all/{firstName:familyName}")
    public List<AppointmentEntity> getAllAppointmentsByFullName(@PathVariable String firstName, @PathVariable String familyName) {
        LOGGER.info("List of appointments booked by '{}' :\n", String.format("%s %s", firstName, familyName));
        List<AppointmentEntity> appointmentEntities = appointmentService.getAppointmentsByClientFullName(firstName, familyName);
        return appointmentEntities;
    }

    @PostMapping("/create")
    public AppointmentEntity createAppointment(@RequestBody AppointmentEntity appointment) throws BadRequestException {
        try {
            AppointmentEntity appointmentEntity = appointmentService.createAppointment(appointment);
            String body = String.format("New appointment has been successfully created");
            return appointmentEntity;
        } catch (Exception e) {
            LOGGER.error("Unable to create appointment");
            throw new BadRequestException("Unable to create appointment");
        }
    }

    @PutMapping("/update/{id}")
    public AppointmentEntity updateAppointmentById(@PathVariable String id, @RequestBody AppointmentEntity updatedAppointment) throws BadRequestException {
        try {
            AppointmentEntity appointmentEntity = appointmentService.updateAppointment(id, updatedAppointment);
            return appointmentEntity;
        } catch (Exception e) {
            LOGGER.error("Unable to update appointment");
            throw new BadRequestException("Unable to update appointment");
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAppointmentById(@PathVariable String id) throws NoSuchElementException {
        try {
            AppointmentEntity entity = appointmentService.deleteAppointment(id);
            String body = String.format("Appointment with id '%s' has been successfully deleted\n", id);
            return body;
        } catch (NoSuchElementException exception) {
            LOGGER.error("Unable to delete appointment with id '{}'. Please verify that the appointment actually exists.", id);
            throw new NoSuchElementException("Unable to delete appointment");
        } catch (Exception exception) {
            LOGGER.error("Internal server error.");
            throw new RuntimeException(exception.getMessage());
        }
    }
}
