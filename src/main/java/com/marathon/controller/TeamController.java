package com.marathon.controller;

import com.marathon.domain.Team;
import com.marathon.domain.dto.TeamDto;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addTeam (@RequestBody TeamDto teamDto) {
        Team team = teamMapper.mapToTeam(teamDto);
        teamDbService.save(team);
        return ResponseEntity.ok(team.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTeam (@RequestBody TeamDto teamDto) {
        Team team = teamMapper.mapToTeam(teamDto);
        teamDbService.save(team);
        return ResponseEntity.ok().build();
    }

}
