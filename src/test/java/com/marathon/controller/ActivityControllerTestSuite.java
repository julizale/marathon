package com.marathon.controller;

import com.marathon.domain.Activity;
import com.marathon.domain.User;
import com.marathon.domain.dto.ActivityDto;
import com.marathon.mapper.ActivityMapper;
import com.marathon.service.ActivityDbService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(ActivityController.class)
class ActivityControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ActivityDbService dbService;
    @MockBean
    private ActivityMapper mapper;

    @Test
    void shouldFetchEmptyList() throws  Exception {
        //Given
        when(dbService.getAllActivities()).thenReturn(List.of());

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/activity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchActivityList() throws Exception {
        //Given
        LocalDateTime dateTime = LocalDateTime.now();
        List<Activity> activityList = List.of(new Activity(112L, dateTime, new User(), "test"));
        List<ActivityDto> activityDtoList = List.of(new ActivityDto(112L, dateTime, 112L, "test"));

        when(mapper.mapToActivityDtoList(anyList())).thenReturn(activityDtoList);
        when(dbService.getAllActivities()).thenReturn(activityList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/activity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].log", Matchers.is("test")));
    }
}




