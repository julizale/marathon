package com.marathon.controller;

import com.marathon.domain.dto.ActivityDto;
import com.marathon.mapper.ActivityMapper;
import com.marathon.service.ActivityDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/activity")
public class ActivityController {

    @Autowired
    private ActivityDbService activityDbService;
    @Autowired
    private ActivityMapper activityMapper;

    @GetMapping
    public ResponseEntity<List<ActivityDto>> getAllActivities () {
        return ResponseEntity.ok(activityMapper.mapToActivityDtoList(activityDbService.getAllActivities()));
    }
}
