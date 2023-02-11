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
        dbService.save(user);
        long id = user.getId();

        //When
        user.setCity("New city");
        Performance performance = new Performance();
        user.setPerformance(performance);
        performance.setUser(user);

        dbService.save(user);
        User retrievedUser = dbService.getUser(id);

        //Then
        assertEquals(retrievedUser, user);
        assertEquals(performance, user.getPerformance());

        //CleanUp
        dbService.deleteUser(id);
    }


}

