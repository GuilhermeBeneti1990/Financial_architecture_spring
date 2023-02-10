package com.beneti.limitsvc.services;

import com.beneti.limitsvc.entities.DiaryLimit;
import com.beneti.limitsvc.repositories.DiaryLimitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiaryLimitService {

    DiaryLimitRepository diaryLimitRepository;

    public DiaryLimitService(DiaryLimitRepository diaryLimitRepository) {
        this.diaryLimitRepository = diaryLimitRepository;
    }

    public Optional<DiaryLimit> findById(Long id) {
        return diaryLimitRepository.findById(id);
    }
}
