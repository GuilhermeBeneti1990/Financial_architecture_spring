package com.beneti.transactionbff.feign;

import com.beneti.transactionbff.dto.DiaryLimit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "limits", url = "${limits.url}")
public interface DiaryLimitClient {

    @GetMapping(path = "/diary-limit/{agency}/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
    DiaryLimit getDiaryLimit(@PathVariable("agency") final Long agency, @PathVariable("account") final Long account);
}
