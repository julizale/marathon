package com.marathon;

import com.marathon.domain.Team;
import com.marathon.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class TeamTestSuite {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void testTeamRepositoryFindById() {
        //Given
        Team team = new Team();
        teamRepository.save(team);
        long id = team.getId();

        //When
        Optional<Team> foundById = teamRepository.findById(id);

        //Then
        assertTrue(foundById.isPresent());

        //Cleanup
        teamRepository.deleteById(id);
    }

    @Test
    void testTeamRepositorySave() {
        //Given
        Team team = new Team();

        //When
        teamRepository.save(team);
        long id = team.getId();
        //Then
        assertNotEquals(0, id);

        //CleanUp
        teamRepository.deleteById(id);
    }

    @Test
    void testTeamRepositoryFindAll() {
        //Given
        Team team1 = new Team();
        Team team2 = new Team();
        teamRepository.save(team1);
        teamRepository.save(team2);
        long id1 = team1.getId();
        long id2 = team2.getId();

        //When
        List<Team> teams = teamRepository.findAll();
        List<Long> iDs = teams.stream()
                .map(Team::getId)
                .toList();

        //Then
        try {
            assertTrue(iDs.contains(id1) && iDs.contains(id2));
            assertEquals(2, iDs.size());
        } finally {
            //CleanUp
            teamRepository.deleteById(id1);
            teamRepository.deleteById(id2);
        }
    }
}
