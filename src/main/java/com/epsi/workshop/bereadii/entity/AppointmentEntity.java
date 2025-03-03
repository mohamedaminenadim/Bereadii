package com.epsi.workshop.bereadii.entity;

import com.epsi.workshop.bereadii.utils.AppointmentStatus;
import com.epsi.workshop.bereadii.utils.ProfessionalService;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Document(collection = "appointments")
public class AppointmentEntity {
    @Id
    @Getter
    private UUID id;
    @NonNull
    public Long clientId;
    @NonNull
    public Long professionalId;
    public ProfessionalService services;
    public LocalDateTime appointmentDateTime;
    public AppointmentStatus status;
    public LocalDateTime createdAt;

    public void setId(UUID id) {
        if (this.id != null) {
            throw new UnsupportedOperationException("ID is already defined");
        }
        this.id = id;
    }

}
