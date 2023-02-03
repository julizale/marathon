package com.marathon.service;

import com.marathon.domain.Activity;
import com.marathon.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActivityDbServiceTest {

    @Mock
    private ActivityRepository activityRepository;
    @InjectMocks
    private ActivityDbService dbService;

    @Test
    void shouldFetchEmptyList() {
        //Given
        when(activityRepository.findAll()).thenReturn(List.of());

        //When
        List<Activity> retrievedList = dbService.getActivitiesByUser(10L);
        //Then
        assertNotNull(retrievedList);
        assertEquals(0, retrievedList.size());
    }

    @Test
    void testDbServiceThrows() {
        //Given
        when(activityRepository.findAll()).thenReturn(null);

        //When & Then
        assertThrows(Exception.class, () -> dbService.getActivitiesByUser(120));
    }
}
