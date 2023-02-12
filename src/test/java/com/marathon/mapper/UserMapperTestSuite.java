package com.marathon.mapper;

import com.marathon.domain.User;
import com.marathon.domain.dto.UserDto;
import com.marathon.domain.enumerated.Sex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTestSuite {

    @Autowired
    private UserMapper mapper;

    @Test
    void shouldMapToDtoList() {
        //Given
        List<User> userList = List.of(new User(112L, "test@test.pl", "test", "test", LocalDate.of(2000, 8, 8),
                "test", Sex.MALE, "test", null, null, new ArrayList<>()));

        //When
        List<UserDto> mappedUserDtoList = mapper.mapToUserDtoList(userList);

        //Then
        assertEquals(112, mappedUserDtoList.get(0).getId());
        assertEquals("test", mappedUserDtoList.get(0).getCity());
        assertEquals("test@test.pl", mappedUserDtoList.get(0).getEmail());
        assertEquals("test", mappedUserDtoList.get(0).getFirstName());
        assertEquals("test", mappedUserDtoList.get(0).getLastName());
        assertEquals("test", mappedUserDtoList.get(0).getPassword());
        assertEquals(LocalDate.of(2000, 8, 8), mappedUserDtoList.get(0).getBirthDate());
        assertEquals(Sex.MALE, mappedUserDtoList.get(0).getSex());
    }

}