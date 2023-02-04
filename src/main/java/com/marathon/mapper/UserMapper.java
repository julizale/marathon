package com.marathon.mapper;

import com.marathon.domain.Performance;
import com.marathon.domain.Team;
import com.marathon.domain.User;
import com.marathon.domain.dto.UserDto;
import com.marathon.repository.PerformanceRepository;
import com.marathon.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class UserMapper {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PerformanceRepository performanceRepository;

    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getBirthDate(),
                userDto.getSex(),
                userDto.getCity(),
                teamRepository.findById(userDto.getTeamId()).orElse(null),
                performanceRepository.findById(userDto.getPerformanceId()).orElse(null),
                new ArrayList<>()
        );
    }

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getSex(),
                user.getCity()
        );
        ofNullable(user.getPerformance()).ifPresent(performance -> userDto.setPerformanceId(performance.getId()));
        ofNullable(user.getTeam()).ifPresent(team -> userDto.setTeamId(team.getId()));
        return userDto;
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .toList();
    }
}
