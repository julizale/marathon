package com.marathon.service;

import com.marathon.repository.PerformanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
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
        when(repository.findById(anyLong())).thenReturn(null);

        //When & Then
        assertThrows(Exception.class, () -> dbService.getPerformance(112L));
    }
}
