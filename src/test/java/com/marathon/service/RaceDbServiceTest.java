package com.marathon.service;

import com.marathon.domain.Activity;
import com.marathon.domain.Race;
import com.marathon.repository.PerformanceRepository;
import com.marathon.repository.RaceRepository;
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
public class RaceDbServiceTest {

    @Mock
    private RaceRepository repository;
    @InjectMocks
    private RaceDbService dbService;

    @Test
    void shouldFetchEmptyList() {
        //Given
        when(repository.findAll()).thenReturn(List.of());

        //When
        List<Race> retrievedList = dbService.getAllRaces();
        //Then
        assertNotNull(retrievedList);
        assertEquals(0, retrievedList.size());
    }

    @Test
    void testDbServiceThrows() {
        //Given
        when(repository.findById(any())).thenReturn(null);

        //When & Then
        assertThrows(Exception.class, () -> dbService.getRace(10L));
    }
}
