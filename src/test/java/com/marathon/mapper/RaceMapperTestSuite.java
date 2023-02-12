package com.marathon.mapper;

import com.marathon.domain.Activity;
import com.marathon.domain.Race;
import com.marathon.domain.User;
import com.marathon.domain.dto.ActivityDto;
import com.marathon.domain.dto.RaceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RaceMapperTestSuite {

    @Autowired
    private RaceMapper mapper;

    @Test
    void shouldMapToDtoList() {
        //Given
        List<Race> raceList = List.of(new Race(112L, "test", 1000L, BigDecimal.TEN, new ArrayList<>()));

        //When
        List<RaceDto> mappedRaceDtoList = mapper.mapToRaceDtoList(raceList);

        //Then
        assertEquals(112, mappedRaceDtoList.get(0).getId());
        assertEquals(1000, mappedRaceDtoList.get(0).getDistance());
        assertEquals("test", mappedRaceDtoList.get(0).getName());
        assertEquals(BigDecimal.TEN, mappedRaceDtoList.get(0).getPrice());
    }

}