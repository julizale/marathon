package com.marathon.service;

import com.marathon.domain.Team;
import com.marathon.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamDbServiceTest {

    @Mock
    private TeamRepository repository;
    @InjectMocks
    private TeamDbService dbService;

    @Test
    void testDbServiceSave() {
        //Given
        Team team = new Team();
        when(repository.save(team)).thenReturn(team);

        //When
        Team retrievedTeam = dbService.save(team);

        //Then
        assertEquals(team, retrievedTeam);
    }

    @Test
    void testDbServiceThrows() {
        //Given
        when(repository.findById(any())).thenReturn(null);

        //When & Then
        assertThrows(Exception.class, () -> dbService.getTeam(10L));
    }
}
