package com.marathon.controller;

import com.marathon.domain.Performance;
import com.marathon.domain.dto.PerformanceDto;
import com.marathon.exception.PerformanceNotFoundException;
import com.marathon.mapper.PerformanceMapper;
import com.marathon.service.PerformanceDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/performance")
public class PerformanceController {

    @Autowired
    private PerformanceDbService performanceDbService;
    @Autowired
    private PerformanceMapper performanceMapper;

    @GetMapping
    public ResponseEntity<List<PerformanceDto>> getAllPerformances () {
        List<PerformanceDto> performanceDtos = performanceMapper.mapToPerformanceDtoList(performanceDbService.getAllPerformances());
        return ResponseEntity.ok(performanceDtos);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PerformanceDto> getPerformance(@PathVariable long id) throws PerformanceNotFoundException {
        PerformanceDto performanceDto = performanceMapper.mapToPerformanceDto(performanceDbService.getPerformance(id));
        return ResponseEntity.ok(performanceDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPerformance (@RequestBody PerformanceDto performanceDto) throws Exception {
        Performance performance = performanceMapper.mapToPerformance(performanceDto);
        performanceDbService.save(performance);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PerformanceDto> updatePerformance (@RequestBody PerformanceDto performanceDto) throws Exception {
        Performance performance = performanceMapper.mapToPerformance(performanceDto);
        Performance updatedPerformance = performanceDbService.save(performance);
        return ResponseEntity.ok(performanceMapper.mapToPerformanceDto(updatedPerformance));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deletePerformance(@PathVariable Long id) {
        performanceDbService.deletePerformance(id);
        return ResponseEntity.ok().build();
    }

}
