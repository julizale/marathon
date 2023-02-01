package com.marathon.repository;

import com.marathon.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TeamRepository extends CrudRepository<Team, Long> {
}
