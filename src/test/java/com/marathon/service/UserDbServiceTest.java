package com.marathon.service;

import com.marathon.domain.Team;
import com.marathon.domain.User;
import com.marathon.exception.UserNotFoundException;
import com.marathon.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
}