package com.beneti.limitsvc.controllers;

import com.beneti.limitsvc.entities.DiaryLimit;
import com.beneti.limitsvc.services.DiaryLimitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/diary-limit")
public class DiaryLimitController {

    DiaryLimitService diaryLimitService;

    public DiaryLimitController(DiaryLimitService diaryLimitService) {
        this.diaryLimitService = diaryLimitService;
    }

    @GetMapping("/{id}")
    public DiaryLimit findById(@PathVariable("id") Long id) {
        Optional<DiaryLimit> diaryLimit = diaryLimitService.findById(id);

        if(diaryLimit.isPresent()) {
            return diaryLimit.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
    }

}
