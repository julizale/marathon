package com.marathon.mapper;

import com.marathon.domain.Race;
import com.marathon.domain.dto.RaceDto;
import com.marathon.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RaceMapper {

    public Race mapToRace(RaceDto raceDto) {
        return new Race(
                raceDto.getId(),
                raceDto.getName(),
                raceDto.getDistance(),
                raceDto.getPrice(),
                new ArrayList<>()
        );
    }

    public RaceDto mapToRaceDto(Race race) {
        return new RaceDto(
                race.getId(),
                race.getName(),
                race.getDistance(),
                race.getPrice()
        );
    }
}
