package com.marathon.controller;

import com.google.gson.Gson;
import com.marathon.domain.Team;
import com.marathon.domain.dto.TeamDto;
import com.marathon.mapper.TeamMapper;
import com.marathon.service.TeamDbService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TeamController.class)
public class TeamControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TeamDbService dbService;
    @MockBean
    private TeamMapper mapper;

    @Test
    void shouldFetchEmptyTeamList() throws  Exception {
        //Given
        when(dbService.getAllTeams()).thenReturn(List.of());

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/team")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }


    @Test
    void shouldFetchTeamList() throws Exception {
        //Given
        List<Team> teamList = List.of(new Team(112L, "test", new ArrayList<>()));
        List<TeamDto> teamDtoList = List.of(new TeamDto(112L, "test"));

        when(mapper.mapToTeamDtoList(anyList())).thenReturn(teamDtoList);
        when(dbService.getAllTeams()).thenReturn(teamList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/team")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")));
    }

    @Test
    void shouldFetchTeamWithGivenId() throws Exception {
        //Given
        TeamDto teamDto = new TeamDto(112L, "test");
        Team team = new Team();
        team.setId(112L);
        team.setName("test");
        team.setUserList(new ArrayList<>());

        when(dbService.getTeam(anyLong())).thenReturn(team);
        when(mapper.mapToTeamDto(any(Team.class))).thenReturn(teamDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/team/112")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")));
    }

    @Test
    void shouldDeleteTeam() throws Exception {
        //Given, When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/team/112"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void shouldCreateTeam() throws Exception {
        //Given
        TeamDto teamDto = new TeamDto(112L, "test");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(teamDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/team")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateTeam() throws Exception {
        //Given
        Team team = new Team(112L, "test", new ArrayList<>());
        TeamDto teamDto = new TeamDto(112L, "test");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(teamDto);

        when(dbService.save(any(Team.class))).thenReturn(team);
        when(mapper.mapToTeam(any(TeamDto.class))).thenReturn(team);
        when(mapper.mapToTeamDto(any(Team.class))).thenReturn(teamDto);

        //When & then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/team")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(112)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")));
    }
}






