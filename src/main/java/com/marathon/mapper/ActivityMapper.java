package com.marathon.mapper;

import com.marathon.domain.Activity;
import com.marathon.domain.dto.ActivityDto;
import com.marathon.exception.UserNotFoundException;
import com.marathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityMapper {

    @Autowired
    private UserRepository userRepository;

    public Activity mapToActivity(ActivityDto activityDto) throws UserNotFoundException {
        return new Activity(
                activityDto.getId(),
                activityDto.getDateTime(),
                userRepository.findById(activityDto.getUserId()).orElseThrow(UserNotFoundException::new),
                activityDto.getLog()
        );
    }

    public ActivityDto mapToActivityDto(Activity activity) {
        return new ActivityDto(
                activity.getId(),
                activity.getDateTime(),
                activity.getUser().getId(),
                activity.getLog()
        );
    }
}

