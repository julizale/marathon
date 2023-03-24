package com.marathon.repository;

import com.marathon.domain.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

}
