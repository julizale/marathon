package com.marathon.mapper;

import com.marathon.domain.Performance;
import com.marathon.domain.Race;
import com.marathon.domain.User;
import com.marathon.domain.dto.PerformanceDto;
import com.marathon.domain.enumerated.StartStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PerformanceMapperTestSuite {

    @Autowired
    private PerformanceMapper mapper;

    @Test
    void shouldMapToDtoList() {
        //Given
        User user = new User();
        user.setId(112L);
        Race race = new Race();
        race.setId(112L);
        List<Performance> performanceList = List.of(new Performance(112L, user, race, false,
                0, StartStatus.DNS, BigDecimal.ZERO, BigDecimal.ZERO));

        //When
        List<PerformanceDto> mappedPerformanceDtoList = mapper.mapToPerformanceDtoList(performanceList);

        //Then
        assertEquals(112, mappedPerformanceDtoList.get(0).getId());
        assertEquals(112, mappedPerformanceDtoList.get(0).getRaceId());
        assertEquals(112, mappedPerformanceDtoList.get(0).getUserId());
        assertEquals(0, mappedPerformanceDtoList.get(0).getBibNumber());
        assertFalse(mappedPerformanceDtoList.get(0).isPaid());
        assertEquals(StartStatus.DNS, mappedPerformanceDtoList.get(0).getStatus());
        assertEquals(BigDecimal.ZERO, mappedPerformanceDtoList.get(0).getTimeNet());
        assertEquals(BigDecimal.ZERO, mappedPerformanceDtoList.get(0).getTimeGross());
    }
}