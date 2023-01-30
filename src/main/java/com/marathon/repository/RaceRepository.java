package com.marathon.repository;

import com.marathon.domain.Race;
import org.springframework.data.repository.CrudRepository;

public interface RaceRepository extends CrudRepository<Race, Long> {
}
