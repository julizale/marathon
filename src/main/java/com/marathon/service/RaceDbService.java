package com.marathon.service;

import com.marathon.domain.Race;
import com.marathon.exception.RaceNotFoundException;
import com.marathon.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceDbService {

    @Autowired
    private RaceRepository raceRepository;

    public Race save(Race race) {
        return save(race);
    }

    public Race getRace(final long raceId) throws RaceNotFoundException {
        return raceRepository.findById(raceId).orElseThrow(RaceNotFoundException::new);
    }

    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }
}
