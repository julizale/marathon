package com.marathon.controller;

import com.marathon.domain.Performance;
import com.marathon.domain.dto.PerformanceDto;
import com.marathon.mapper.PerformanceMapper;
import com.marathon.service.PerformanceDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/performance")
public class PerformanceController {

    @Autowired
    private PerformanceDbService performanceDbService;
    @Autowired
    private PerformanceMapper performanceMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addPerformance (@RequestBody PerformanceDto performanceDto) throws Exception {
        Performance performance = performanceMapper.mapToPerformance(performanceDto);
        performanceDbService.save(performance);
        return ResponseEntity.ok(performance.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerformance (@RequestBody PerformanceDto performanceDto) throws Exception {
        Performance performance = performanceMapper.mapToPerformance(performanceDto);
        performanceDbService.save(performance);
        return ResponseEntity.ok().build();
    }

}
