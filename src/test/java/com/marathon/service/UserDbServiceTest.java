package com.marathon.service;

import com.marathon.domain.Sex;
import com.marathon.domain.User;
import com.marathon.exception.UserNotFoundException;
import com.marathon.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
class UserDbServiceTest {

    UserRepository repository;
    @Autowired
    UserDbService dbService;

    @Test
    void testDbServiceSave() throws UserNotFoundException {
        //Given
        User user = new User();

        //When
        dbService.save(user);
        long id = user.getId();
        User retrievedUser = dbService.getUser(id);

        //Then
        assertEquals(id, retrievedUser.getId());
    }

    @Test
    void testCreateUserAdmin() {
        //Given
        User admin = User.builder()
                .email("julizale@protonmail.com")
                .birthDate(LocalDate.of(2001, 01, 01))
                .firstName("Julian")
                .lastName("Załęski")
                .sex(Sex.MALE)
                .city("Częstochowa")
                .build();

        //When
        dbService.save(admin);

        //Then

    }
}

