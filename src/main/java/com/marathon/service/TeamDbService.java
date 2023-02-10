package com.marathon.service;

import com.marathon.domain.Team;
import com.marathon.exception.TeamNotFoundException;
import com.marathon.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamDbService {

    @Autowired
    private TeamRepository teamRepository;

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team getTeam(long id) throws TeamNotFoundException {
        return teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
