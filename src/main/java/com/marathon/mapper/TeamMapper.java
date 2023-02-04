package com.marathon.mapper;

import com.marathon.domain.Team;
import com.marathon.domain.dto.TeamDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamMapper {

    public Team mapToTeam(TeamDto teamDto) {
        return new Team(
                teamDto.getId(),
                teamDto.getName(),
                new ArrayList<>()
        );
    }

    public TeamDto mapToTeamDto(Team team) {
        return new TeamDto(team.getId(), team.getName());
    }

    public List<TeamDto> mapToTeamDtoList(List<Team> teams) {
        return teams.stream()
                .map(this::mapToTeamDto)
                .toList();
    }
}
