package com.epsi.workshop.bereadii.controller;

import com.epsi.workshop.bereadii.entity.AppointmentEntity;
import com.epsi.workshop.bereadii.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment/")
public class AppointmentController {
    @Autowired
    public AppointmentService appointmentService;

    @GetMapping("/all")
    public List<AppointmentEntity> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/all/{firstName:familyName}")
    public List<AppointmentEntity> getAllAppointmentsByFullName(@PathVariable String firstName, @PathVariable String familyName) {
        return appointmentService.getAppointmentsByClientFullName(firstName, familyName);
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentEntity appointment) {
        try {
            AppointmentEntity appointmentEntity = appointmentService.createAppointment(appointment);
            return ResponseEntity.ok(appointmentService.createAppointment(appointment));
        } catch (Exception exception) {
            String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AppointmentEntity> updateAppointmentById(@PathVariable String id, @RequestBody AppointmentEntity updatedAppointment) {
        try {
            AppointmentEntity appointmentEntity = appointmentService.updateAppointment(id, updatedAppointment);
            return ResponseEntity.ok(appointmentEntity);
        } catch (Exception exception) {
            String.format("An exception of type %s occured. Here is the cause :\n%s",exception.getClass(), exception.getMessage());
        }
        return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointmentById(@PathVariable String id) {
        try {
            AppointmentEntity appointmentEntity = appointmentService.deleteAppointment(id);
        }
        return ResponseEntity.ok("Appointment deleted successfully");
    }
}
