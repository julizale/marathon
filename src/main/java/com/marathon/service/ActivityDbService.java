package com.marathon.service;

import com.marathon.domain.Activity;
import com.marathon.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityDbService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public List<Activity> getActivitiesByUser(final long userId) {
        return activityRepository.findAll().stream()
                .filter(activity -> activity.getUser().getId() == userId)
                .toList();
    }
}
