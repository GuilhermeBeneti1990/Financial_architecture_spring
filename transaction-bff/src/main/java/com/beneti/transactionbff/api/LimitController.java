package com.beneti.transactionbff.api;

import com.beneti.transactionbff.dto.DiaryLimit;
import com.beneti.transactionbff.services.LimitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class LimitController {

    private LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    @GetMapping("/{agency}/{account}")
    public DiaryLimit getDiaryLimit(final Long agency, final Long account) {
        return limitService.getDiaryLimit(agency, account);
    }

}
