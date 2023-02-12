package com.marathon.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.marathon.adapter.LocalDateAdapter;
import com.marathon.domain.Performance;
import com.marathon.domain.enumerated.Sex;
import com.marathon.domain.Team;
import com.marathon.domain.User;
import com.marathon.domain.dto.UserDto;
import com.marathon.mapper.UserMapper;
import com.marathon.service.UserDbService;
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

import java.time.LocalDate;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDbService dbService;
    @MockBean
    private UserMapper userMapper;

    @Test
    void shouldFetchEmptyUserList() throws  Exception {
        //Given
        when(dbService.getAllUsers()).thenReturn(List.of());

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchUserList() throws Exception {
        //Given
        List<User> userList = List.of(User.builder()
                .id(12L)
                .email("test@test.pl")
                .firstName("test")
                .lastName("test")
                .password("test")
                .city("test")
                .sex(Sex.MALE)
                .team(new Team())
                .performance(new Performance())
                .birthDate(LocalDate.of(2000,8,8))
                .build());

        UserDto userDto = new UserDto();
        userDto.setId(12L);
        userDto.setTeamId(1L);
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setCity("test");
        userDto.setSex(Sex.MALE);
        userDto.setPassword("test");
        userDto.setPerformanceId(1L);
        userDto.setEmail("test@test.pl");
        userDto.setBirthDate(LocalDate.of(2000,8,8));
        List<UserDto> userDtoList = List.of(userDto);

        when(userMapper.mapToUserDtoList(anyList())).thenReturn(userDtoList);
        when(dbService.getAllUsers()).thenReturn(userList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("test@test.pl")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].birthDate", Matchers.is("2000-08-08")));
    }


    @Test
    void shouldFetchUserWithGivenId() throws Exception {
        //Given
        UserDto userDto = new UserDto();
        userDto.setId(12L);
        userDto.setTeamId(1L);
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setCity("test");
        userDto.setSex(Sex.MALE);
        userDto.setPassword("test");
        userDto.setPerformanceId(1L);
        userDto.setEmail("test@test.pl");
        userDto.setBirthDate(LocalDate.of(2000,8,8));

        User user = User.builder()
                .id(12L)
                .email("test@test.pl")
                .firstName("test")
                .lastName("test")
                .password("test")
                .city("test")
                .sex(Sex.MALE)
                .team(new Team())
                .performance(new Performance())
                .birthDate(LocalDate.of(2000,8,8))
                .build();

        when(dbService.getUser(anyLong())).thenReturn(user);
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(userDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/user/12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("test@test.pl")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate", Matchers.is("2000-08-08")));
    }


    @Test
    void shouldDeleteUser() throws Exception {
        //Given, When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/user/12"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldCreateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto();
        userDto.setId(12L);
        userDto.setTeamId(1L);
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setCity("test");
        userDto.setSex(Sex.MALE);
        userDto.setPassword("test");
        userDto.setPerformanceId(1L);
        userDto.setEmail("test@test.pl");
        userDto.setBirthDate(LocalDate.of(2000,8,8));

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto();
        userDto.setId(12L);
        userDto.setTeamId(1L);
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setCity("test");
        userDto.setSex(Sex.MALE);
        userDto.setPassword("test");
        userDto.setPerformanceId(1L);
        userDto.setEmail("test@test.pl");
        userDto.setBirthDate(LocalDate.of(2000,8,8));

        User user = User.builder()
                .id(12L)
                .email("test@test.pl")
                .firstName("test")
                .lastName("test")
                .password("test")
                .city("test")
                .sex(Sex.MALE)
                .team(new Team())
                .performance(new Performance())
                .birthDate(LocalDate.of(2000,8,8))
                .build();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(userDto);

        when(dbService.save(any(User.class))).thenReturn(user);
        when(userMapper.mapToUser(any(UserDto.class))).thenReturn(user);
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(userDto);

        //When & then
        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("test@test.pl")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate", Matchers.is("2000-08-08")));
    }

}
