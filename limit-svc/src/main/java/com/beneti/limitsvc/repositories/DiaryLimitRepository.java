package com.beneti.limitsvc.repositories;

import com.beneti.limitsvc.entities.DiaryLimit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryLimitRepository extends CrudRepository<DiaryLimit, Long> {

    DiaryLimit findByAgencyAndAccount(Long agency, Long account);
}
