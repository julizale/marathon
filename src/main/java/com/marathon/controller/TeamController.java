package com.marathon.controller;

import com.marathon.domain.Team;
import com.marathon.domain.dto.TeamDto;
import com.marathon.domain.dto.UserDto;
import com.marathon.exception.TeamNotFoundException;
import com.marathon.exception.UserNotFoundException;
import com.marathon.mapper.TeamMapper;
import com.marathon.service.TeamDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/team")
public class TeamController {

    @Autowired
    private TeamDbService teamDbService;
    @Autowired
    private TeamMapper teamMapper;

    @GetMapping
    public ResponseEntity<List<TeamDto>> getTeams () {
        return ResponseEntity.ok(teamMapper.mapToTeamDtoList(teamDbService.getAllTeams()));
    }

    @GetMapping(value = "{teamId}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable Long teamId) throws TeamNotFoundException {
        TeamDto teamDto = teamMapper.mapToTeamDto(teamDbService.getTeam(teamId));
        return ResponseEntity.ok(teamDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTeam (@RequestBody TeamDto teamDto) {
        Team team = teamMapper.mapToTeam(teamDto);
        teamDbService.save(team);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDto> updateTeam (@RequestBody TeamDto teamDto) {
        Team team = teamMapper.mapToTeam(teamDto);
        Team updatedTeam = teamDbService.save(team);
        return ResponseEntity.ok(teamMapper.mapToTeamDto(updatedTeam));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) throws TeamNotFoundException {
        teamDbService.deleteTeam(id);
        return ResponseEntity.ok().build();
    }
}
