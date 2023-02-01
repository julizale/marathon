package com.marathon.mapper;

import com.marathon.domain.User;
import com.marathon.domain.dto.UserDto;
import com.marathon.exception.PerformanceNotFoundException;
import com.marathon.exception.TeamNotFoundException;
import com.marathon.repository.PerformanceRepository;
import com.marathon.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserMapper {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PerformanceRepository performanceRepository;

    public User mapToUser(UserDto userDto) throws TeamNotFoundException, PerformanceNotFoundException {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getBirthDate(),
                userDto.getSex(),
                userDto.getCity(),
                teamRepository.findById(userDto.getTeamId()).orElseThrow(TeamNotFoundException::new),
                performanceRepository.findById(userDto.getPerformanceId()).orElseThrow(PerformanceNotFoundException::new),
                new ArrayList<>()
        );
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getSex(),
                user.getCity(),
                user.getTeam().getId(),
                user.getPerformance().getId()
        );
    }
}
