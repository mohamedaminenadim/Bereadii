package com.epsi.workshop.bereadii.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    private UUID id;
    private String firstName;
    private String familyName;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String username;

    public UserEntity(UserEntity updatedUser) {
        this.firstName = updatedUser.firstName;
        this.familyName = updatedUser.familyName;
        this.email = updatedUser.email;
        this.phoneNumber = updatedUser.phoneNumber;
        this.updatedAt = updatedUser.updatedAt;
    }

    public String getId() {
        return this.id.toString();
    }
}
