package com.marathon.mapper;

import com.marathon.domain.Activity;
import com.marathon.domain.Team;
import com.marathon.domain.User;
import com.marathon.domain.dto.ActivityDto;
import com.marathon.domain.dto.TeamDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeamMapperTestSuite {

    @Autowired
    private TeamMapper mapper;

    @Test
    void shouldMapToDtoList() {
        //Given
        List<Team> teamList = List.of(new Team(112L, "test", new ArrayList<>()));

        //When
        List<TeamDto> mappedTeamDtoList = mapper.mapToTeamDtoList(teamList);

        //Then
        assertEquals(112, mappedTeamDtoList.get(0).getId());
        assertEquals("test", mappedTeamDtoList.get(0).getName());
    }

}