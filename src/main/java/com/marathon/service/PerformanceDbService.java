package com.marathon.service;

import com.marathon.domain.Performance;
import com.marathon.exception.PerformanceByUserExistsException;
import com.marathon.exception.PerformanceNotFoundException;
import com.marathon.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceDbService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public Performance save(Performance performance) throws PerformanceByUserExistsException {
        Optional<Performance> optionalPerformance = getAllPerformances().stream().
                filter(p -> performance.getUser().getId().equals(p.getUser().getId()))
                .findAny();
        if (optionalPerformance.isPresent()) {
            Performance exOptionalPerformance = optionalPerformance.get();
            if (!exOptionalPerformance.getId().equals(performance.getId())) {
                throw new PerformanceByUserExistsException();
            }
         }
        return performanceRepository.save(performance);
    }

    public Performance getPerformance(long id) throws PerformanceNotFoundException {
        return performanceRepository.findById(id).orElseThrow(PerformanceNotFoundException::new);
    }

    public List<Performance> getAllPerformances() {
        return performanceRepository.findAll();
    }

    public void deletePerformance(long id) {
        performanceRepository.deleteById(id);
    }

}
