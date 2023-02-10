package com.marathon.mapper;

import com.marathon.domain.Performance;
import com.marathon.domain.dto.PerformanceDto;
import com.marathon.exception.RaceNotFoundException;
import com.marathon.exception.UserNotFoundException;
import com.marathon.repository.RaceRepository;
import com.marathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceMapper {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RaceRepository raceRepository;

    public Performance mapToPerformance(PerformanceDto performanceDto) throws UserNotFoundException, RaceNotFoundException {
        return new Performance(
                performanceDto.getId(),
                userRepository.findById(performanceDto.getUserId()).orElseThrow(UserNotFoundException::new),
                raceRepository.findById(performanceDto.getRaceId()).orElseThrow(RaceNotFoundException::new),
                performanceDto.isPaid(),
                performanceDto.getBibNumber(),
                performanceDto.getStatus(),
                performanceDto.getTimeGross(),
                performanceDto.getTimeNet()
        );
    }

    public PerformanceDto mapToPerformanceDto(Performance performance) {
        return new PerformanceDto(
                performance.getId(),
                performance.getUser().getId(),
                performance.getRace().getId(),
                performance.isPaid(),
                performance.getBibNumber(),
                performance.getStatus(),
                performance.getTimeGross(),
                performance.getTimeNet()
        );
    }

    public List<PerformanceDto> mapToPerformanceDtoList(List<Performance> performances) {
        return performances.stream()
                .map(this::mapToPerformanceDto)
                .toList();
    }
}
