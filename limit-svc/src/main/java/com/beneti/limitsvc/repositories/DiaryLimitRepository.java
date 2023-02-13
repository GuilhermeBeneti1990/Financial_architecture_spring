package com.beneti.limitsvc.repositories;

import com.beneti.limitsvc.entities.DiaryLimit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiaryLimitRepository extends CrudRepository<DiaryLimit, Long> {

    Optional<DiaryLimit> findByAgencyAndAccount(final Long agency, final Long account);
}
