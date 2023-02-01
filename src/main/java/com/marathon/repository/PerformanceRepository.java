package com.marathon.repository;

import com.marathon.domain.Performance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PerformanceRepository extends CrudRepository<Performance, Long> {

    List<Performance> findAll();
}
