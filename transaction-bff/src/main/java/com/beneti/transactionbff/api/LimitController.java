package com.beneti.transactionbff.api;

import com.beneti.transactionbff.dto.DiaryLimit;
import com.beneti.transactionbff.feign.DiaryLimitClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class LimitController {

    private DiaryLimitClient diaryLimitClient;

    public LimitController(DiaryLimitClient diaryLimitClient) {
        this.diaryLimitClient = diaryLimitClient;
    }

    @GetMapping("/{agency}/{account}")
    public DiaryLimit getDiaryLimit(final Long agency, final Long account) {
        return diaryLimitClient.getDiaryLimit(agency, account);
    }

}
