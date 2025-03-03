package com.epsi.workshop.bereadii.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "authentication")
@Getter
@Setter
@Builder(toBuilder = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class UserAuthenticationEntity {
    @Id
    private UUID id;
    private String username;
    private String password;

    public UserAuthenticationEntity(){}
}
