package com.marathon.repository;

import com.marathon.domain.Race;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RaceRepository extends CrudRepository<Race, Long> {

    List<Race> findAll();
}
