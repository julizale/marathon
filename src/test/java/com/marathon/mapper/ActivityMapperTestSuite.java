package com.marathon.mapper;

import com.marathon.domain.Activity;
import com.marathon.domain.User;
import com.marathon.domain.dto.ActivityDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActivityMapperTestSuite {

    @Autowired
    private ActivityMapper mapper;

    @Test
    void shouldMapToDtoList() {
        //Given
        User user = new User();
        user.setId(112L);
        LocalDateTime dateTime = LocalDateTime.now();
        List<Activity> activityList = List.of(new Activity(112L, dateTime, user, "test"));

        //When
        List<ActivityDto> mappedActivityDtoList = mapper.mapToActivityDtoList(activityList);

        //Then
        assertEquals(112, mappedActivityDtoList.get(0).getId());
        assertEquals(dateTime, mappedActivityDtoList.get(0).getDateTime());
        assertEquals("test", mappedActivityDtoList.get(0).getLog());
        assertEquals(112, mappedActivityDtoList.get(0).getUserId());
    }
}