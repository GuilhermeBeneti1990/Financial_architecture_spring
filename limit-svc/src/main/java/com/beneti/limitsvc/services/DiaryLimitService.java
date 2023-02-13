package com.beneti.limitsvc.services;

import com.beneti.limitsvc.entities.DiaryLimit;
import com.beneti.limitsvc.repositories.DiaryLimitRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DiaryLimitService {

    DiaryLimitRepository diaryLimitRepository;

    @Value("${limit.value}")
    private BigDecimal diaryValue;

    public DiaryLimitService(DiaryLimitRepository diaryLimitRepository) {
        this.diaryLimitRepository = diaryLimitRepository;
    }

    public Optional<DiaryLimit> getDiaryLimit(final Long agency, final Long account) {
        final Optional<DiaryLimit> diaryLimit = diaryLimitRepository.findByAgencyAndAccount(agency, account);

        if(diaryLimit.isEmpty()) {
            var limit = new DiaryLimit();
            limit.setValue(diaryValue);
            limit.setAccount(account);
            limit.setAgency(agency);

            return Optional.of(diaryLimitRepository.save(limit));
        }

        return diaryLimit;
    }

    public Optional<DiaryLimit> findById(Long id) {
        return diaryLimitRepository.findById(id);
    }
}
