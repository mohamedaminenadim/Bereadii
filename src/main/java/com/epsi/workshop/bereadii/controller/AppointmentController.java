package com.epsi.workshop.bereadii.controller;

import com.epsi.workshop.bereadii.entity.AppointmentEntity;
import com.epsi.workshop.bereadii.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment/")
@Slf4j
public class AppointmentController {

    private static Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    public AppointmentService appointmentService;

    @GetMapping("/all")
    public List<AppointmentEntity> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentEntity> getAppointmentById(@PathVariable String id) {
        try {
            AppointmentEntity appointmentEntity = appointmentService.getAppointmentById(id);
            LOGGER.info("Found appointment with id " + id );
            return ResponseEntity.status(200).body(appointmentEntity);
        } catch (Exception exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/all/{firstName:familyName}")
    public ResponseEntity<List<AppointmentEntity>> getAllAppointmentsByFullName(@PathVariable String firstName, @PathVariable String familyName) {
        try {
            List<AppointmentEntity> appointmentEntity = appointmentService.getAppointmentsByClientFullName(firstName, familyName);
            return ResponseEntity.status(201).body(appointmentEntity);
        } catch (Exception exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentEntity appointment) {
        try {
            AppointmentEntity appointmentEntity = appointmentService.createAppointment(appointment);
            return ResponseEntity.status(201).body(appointmentEntity);
        } catch (Exception exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AppointmentEntity> updateAppointmentById(@PathVariable String id, @RequestBody AppointmentEntity updatedAppointment) {
        try {
            AppointmentEntity appointmentEntity = appointmentService.updateAppointment(id, updatedAppointment);
            return ResponseEntity.status(200).body(appointmentEntity);
        } catch (Exception exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        }
        return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointmentById(@PathVariable String id) {
        try {
            AppointmentEntity entity = appointmentService.deleteAppointment(id);
            String body = String.format("Appointment with id '%s' has been successfully deleted\n", id);
            return ResponseEntity.status(200).body(body + entity);
        } catch (Exception exception) {
            var message = String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
            LOGGER.error(message);
        }
        return ResponseEntity.status(400).build();
    }
}
