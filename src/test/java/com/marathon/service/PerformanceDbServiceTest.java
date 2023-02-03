package com.marathon.service;

import com.marathon.repository.PerformanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PerformanceDbServiceTest {

    @Mock
    private PerformanceRepository repository;
    @InjectMocks
    private PerformanceDbService dbService;

    @Test
    void testDbServiceThrows() {
        //Given
        when(repository.findAll()).thenReturn(null);

        //When & Then
        assertThrows(Exception.class, () -> dbService.getPerformanceByUser(120));
        assertThrows(Exception.class, () -> dbService.getPerformancesByRace(120));
    }
}
