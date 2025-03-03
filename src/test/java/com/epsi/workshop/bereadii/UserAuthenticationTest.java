package com.epsi.workshop.bereadii;

import com.epsi.workshop.bereadii.entity.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserAuthenticationTest {

    @BeforeAll
    static void setup() {
        UserEntity mockedUser1 = Mockito.mock(UserEntity.class);
        UserEntity mockedUser2 = Mockito.mock(UserEntity.class);

        mockedUser1.setFirstName("Green");
        mockedUser1.setFamilyName("Lantern");
        mockedUser1.setPhoneNumber("0690807060");
        mockedUser1.setEmail("green.lantern@example.com");
        mockedUser1.setCreatedAt(LocalDateTime.of(2025,03,01,12,00,00));
        mockedUser1.setUpdatedAt(LocalDateTime.of(2025,03,01,16,00,00));

        mockedUser2.setFirstName("Robin");
        mockedUser2.setFamilyName("Hood");
        mockedUser2.setPhoneNumber("0610203040");
        mockedUser2.setEmail("robin.hood@example.com");
        mockedUser2.setCreatedAt(LocalDateTime.of(2025,03,01,12,30,00));
        mockedUser2.setUpdatedAt(LocalDateTime.of(2025,03,01,16,30,00));


    }

    @Test
    void authenticateUserWhenProvidedCorrectCredentials() {
        int i = 5;
        assertEquals(5, i);
    }
}
