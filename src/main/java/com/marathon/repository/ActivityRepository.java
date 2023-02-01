package com.marathon.repository;

import com.marathon.domain.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ActivityRepository extends CrudRepository<Activity, Long> {

    List<Activity> findAll();
}
