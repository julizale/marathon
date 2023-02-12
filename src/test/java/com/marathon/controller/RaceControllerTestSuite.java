package com.marathon.controller;

import com.google.gson.Gson;
import com.marathon.domain.Race;
import com.marathon.domain.dto.RaceDto;
import com.marathon.mapper.RaceMapper;
import com.marathon.service.RaceDbService;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(RaceController.class)
public class RaceControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RaceDbService dbService;
    @MockBean
    private RaceMapper mapper;

    @Test
    void shouldFetchEmptyRaceList() throws  Exception {
        //Given
        when(dbService.getAllRaces()).thenReturn(List.of());

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/race")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }


    @Test
    void shouldFetchRaceList() throws Exception {
        //Given
        List<Race> raceList = List.of(new Race(112L, "test", 100L, BigDecimal.TEN, new ArrayList<>()));
        List<RaceDto> raceDtoList = List.of(new RaceDto(112L, "test", 100L, BigDecimal.TEN));

        when(mapper.mapToRaceDtoList(anyList())).thenReturn(raceDtoList);
        when(dbService.getAllRaces()).thenReturn(raceList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/race")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")));
    }


    @Test
    void shouldFetchTeamWithGivenId() throws Exception {
        //Given
        RaceDto raceDto = new RaceDto(112L, "test", 100L, BigDecimal.TEN);
        Race race = new Race();
        race.setId(112L);
        race.setName("test");
        race.setDistance(100L);
        race.setPrice(BigDecimal.TEN);
        race.setPerformanceList(new ArrayList<>());

        when(dbService.getRace(anyLong())).thenReturn(race);
        when(mapper.mapToRaceDto(any(Race.class))).thenReturn(raceDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/race/112")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.distance", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")));
    }

    @Test
    void shouldDeleteRace() throws Exception {
        //Given, When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/race/112"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldCreateRace() throws Exception {
        //Given
        RaceDto raceDto = new RaceDto(112L, "test", 100L, BigDecimal.TEN);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(raceDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/race")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateRace() throws Exception {
        //Given
        RaceDto raceDto = new RaceDto(112L, "test", 100L, BigDecimal.TEN);
        Race race = new Race(112L, "test", 100L, BigDecimal.TEN, new ArrayList<>());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(raceDto);

        when(dbService.save(any(Race.class))).thenReturn(race);
        when(mapper.mapToRace(any(RaceDto.class))).thenReturn(race);
        when(mapper.mapToRaceDto(any(Race.class))).thenReturn(raceDto);

        //When & then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/race")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.distance", Matchers.is(100)));
    }

}
