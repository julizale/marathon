package com.marathon.mapper;

import com.marathon.domain.Race;
import com.marathon.domain.dto.RaceDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<RaceDto> mapToRaceDtoList (List<Race> races) {
        return races.stream()
                .map(this::mapToRaceDto)
                .toList();
    }
}
