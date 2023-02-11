package com.marathon.controller;

import com.marathon.domain.Race;
import com.marathon.domain.dto.RaceDto;
import com.marathon.exception.RaceNotFoundException;
import com.marathon.mapper.RaceMapper;
import com.marathon.service.RaceDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/race")
public class RaceController {

    @Autowired
    private RaceDbService raceDbService;
    @Autowired
    private RaceMapper raceMapper;

    @GetMapping
    public ResponseEntity<List<RaceDto>> getAllRaces () {
        return ResponseEntity.ok(raceMapper.mapToRaceDtoList(raceDbService.getAllRaces()));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<RaceDto> getRace (@PathVariable long id) throws RaceNotFoundException {
        return ResponseEntity.ok(raceMapper.mapToRaceDto(raceDbService.getRace(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addRace (@RequestBody RaceDto raceDto) {
        Race race = raceMapper.mapToRace(raceDto);
        raceDbService.save(race);
        return ResponseEntity.ok(race.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateRace (@RequestBody RaceDto raceDto) {
        Race race = raceMapper.mapToRace(raceDto);
        raceDbService.save(race);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        raceDbService.deleteRace(id);
        return ResponseEntity.ok().build();
    }
}
