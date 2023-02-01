package com.marathon.service;

import com.marathon.domain.Team;
import com.marathon.exception.TeamNotFoundException;
import com.marathon.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
