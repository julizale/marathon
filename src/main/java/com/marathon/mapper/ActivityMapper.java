package com.marathon.mapper;

import com.marathon.domain.Activity;
import com.marathon.domain.dto.ActivityDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityMapper {

    public ActivityDto mapToActivityDto(Activity activity) {
        return new ActivityDto(
                activity.getId(),
                activity.getDateTime(),
                activity.getUser().getId(),
                activity.getLog()
        );
    }

    public List<ActivityDto> mapToActivityDtoList(List<Activity> activities) {
        return activities.stream()
                .map(this::mapToActivityDto)
                .toList();
    }


}

