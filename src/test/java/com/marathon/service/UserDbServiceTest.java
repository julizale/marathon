package com.marathon.service;

import com.marathon.domain.Performance;
import com.marathon.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
class UserDbServiceTest {

    @Autowired
    UserDbService dbService;

    @Test
    void testDbServiceSave() throws Exception {
        //Given
        User user = new User();
        user.setEmail("test@test.com");
        user.setFirstName("test");
        user.setLastName("test");
        user.setCity("test");
        //When
        dbService.save(user);
        long id = user.getId();
        User retrievedUser = dbService.getUser(id);

        //Then
        assertEquals(id, retrievedUser.getId());
    }

    @Test
    void testSaveUserWithIdPresent() throws Exception {
        //Given
        User user = new User();
        user.setEmail("test@test.com");
        user.setFirstName("test");
        user.setLastName("test");
        user.setCity("test");
        dbService.save(user);
        long id = user.getId();

        //When
        user.setCity("Newtestcity");
        dbService.save(user);
        User retrievedUser = dbService.getUser(id);

        //Then
        assertEquals(retrievedUser, user);
        assertEquals("Newtestcity", retrievedUser.getCity());

        //CleanUp
        dbService.deleteUser(id);
    }


}

