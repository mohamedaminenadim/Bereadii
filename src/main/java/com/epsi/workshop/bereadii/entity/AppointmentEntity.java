package com.epsi.workshop.bereadii.entity;

import com.epsi.workshop.bereadii.utils.AppointmentStatus;
import com.epsi.workshop.bereadii.utils.ProfessionalService;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "appointments")
public class AppointmentEntity {
    @Id
    private String id;
    @NonNull
    private Long clientId;
    @NonNull
    private Long professionalId;
    private ProfessionalService services;
    private LocalDateTime appointmentDateTime;
    private AppointmentStatus status;
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }


    public @NonNull Long getClientId() {
        return clientId;
    }

    public void setClientId(@NonNull Long clientId) {
        this.clientId = clientId;
    }

    public @NonNull Long getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(@NonNull Long professionalId) {
        this.professionalId = professionalId;
    }

    public ProfessionalService getServices() {
        return services;
    }

    public void setServices(ProfessionalService services) {
        this.services = services;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
