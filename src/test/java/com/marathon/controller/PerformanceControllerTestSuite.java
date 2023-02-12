package com.marathon.controller;

import com.google.gson.Gson;
import com.marathon.domain.Performance;
import com.marathon.domain.Race;
import com.marathon.domain.User;
import com.marathon.domain.dto.PerformanceDto;
import com.marathon.domain.enumerated.StartStatus;
import com.marathon.mapper.PerformanceMapper;
import com.marathon.service.PerformanceDbService;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(PerformanceController.class)
class PerformanceControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PerformanceDbService dbService;
    @MockBean
    private PerformanceMapper mapper;

    @Test
    void shouldFetchEmptyPerformanceList() throws  Exception {
        //Given
        when(dbService.getAllPerformances()).thenReturn(List.of());

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/performance")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchPerformanceList() throws Exception {
        //Given
        List<Performance> performanceList = List.of(new Performance(112L, new User(), new Race(),
                false, 100, StartStatus.DNS, BigDecimal.ZERO, BigDecimal.ZERO));
        List<PerformanceDto> performanceDtoList = List.of(new PerformanceDto(112L, 112L, 112L,
                false, 100, StartStatus.DNS, BigDecimal.ZERO, BigDecimal.ZERO));

        when(mapper.mapToPerformanceDtoList(anyList())).thenReturn(performanceDtoList);
        when(dbService.getAllPerformances()).thenReturn(performanceList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/performance")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].raceId", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bibNumber", Matchers.is(100)));
    }

    @Test
    void shouldFetchPerformanceWithGivenId() throws Exception {
        //Given
        PerformanceDto performanceDto = new PerformanceDto(112L, 112L, 112L,
                false, 100, StartStatus.DNS, BigDecimal.ZERO, BigDecimal.ZERO);
        Performance performance = new Performance();
        performance.setUser(new User());
        performance.setId(112L);
        performance.setRace(new Race());
        performance.setPaid(false);
        performance.setBibNumber(100);
        performance.setStatus(StartStatus.DNS);
        performance.setTimeGross(BigDecimal.ZERO);
        performance.setTimeNet(BigDecimal.ZERO);

        when(dbService.getPerformance(anyLong())).thenReturn(performance);
        when(mapper.mapToPerformanceDto(any(Performance.class))).thenReturn(performanceDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/performance/112")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.raceId", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bibNumber", Matchers.is(100)));
    }

    @Test
    void shouldDeletePerformance() throws Exception {
        //Given, When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/performance/112"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldCreatePerformance() throws Exception {
        //Given
        PerformanceDto performanceDto = new PerformanceDto(112L, 112L, 112L,
                false, 100, StartStatus.DNS, BigDecimal.ZERO, BigDecimal.ZERO);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(performanceDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/performance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdatePerformance() throws Exception {
        //Given
        Performance performance = new Performance(112L, new User(), new Race(),
                false, 100, StartStatus.DNS, BigDecimal.ZERO, BigDecimal.ZERO);
        PerformanceDto performanceDto = new PerformanceDto(112L, 112L, 112L,
                false, 100, StartStatus.DNS, BigDecimal.ZERO, BigDecimal.ZERO);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(performanceDto);

        when(dbService.save(any(Performance.class))).thenReturn(performance);
        when(mapper.mapToPerformance(any(PerformanceDto.class))).thenReturn(performance);
        when(mapper.mapToPerformanceDto(any(Performance.class))).thenReturn(performanceDto);

        //When & then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/performance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.raceId", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bibNumber", Matchers.is(100)));
    }
}
