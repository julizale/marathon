package com.marathon;

import com.marathon.domain.Activity;
import com.marathon.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ActivityTestSuite {

    @Autowired
    private ActivityRepository activityRepository;



    @Test
    void testActivityRepositoryFindById() {
        //Given
        Activity activity = new Activity();
        activityRepository.save(activity);
        long activityId = activity.getId();

        //When
        Optional<Activity> activityFoundById = activityRepository.findById(activityId);

        //Then
        assertTrue(activityFoundById.isPresent());

        //Cleanup
        activityRepository.deleteById(activityId);
    }

    @Test
    void testActivityRepositorySave() {
        //Given
        Activity activity = new Activity();

        //When
        activityRepository.save(activity);
        long id = activity.getId();
        //Then
        assertNotEquals(0, id);

        //CleanUp
        activityRepository.deleteById(id);
    }

    @Test
    void testActivityRepositoryFindAll() {
        //Given
        Activity activity1 = new Activity();
        Activity activity2 = new Activity();
        activityRepository.save(activity1);
        activityRepository.save(activity2);
        long activity1Id = activity1.getId();
        long activity2Id = activity2.getId();

        //When
        List<Activity> activities = activityRepository.findAll();
        List<Long> iDs = activities.stream()
                .map(Activity::getId)
                .toList();

        //Then
        try {
            assertTrue(iDs.contains(activity1Id) && iDs.contains(activity2Id));
            assertEquals(2, iDs.size());
        } finally {
            //CleanUp
            activityRepository.deleteById(activity1Id);
            activityRepository.deleteById(activity2Id);
        }
    }
}
