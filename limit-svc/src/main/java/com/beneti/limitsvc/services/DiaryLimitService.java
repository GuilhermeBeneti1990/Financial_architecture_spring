package com.beneti.limitsvc.services;

import com.beneti.limitsvc.dto.TransactionDto;
import com.beneti.limitsvc.entities.DiaryLimit;
import com.beneti.limitsvc.events.LimitProducer;
import com.beneti.limitsvc.repositories.DiaryLimitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class DiaryLimitService {

    DiaryLimitRepository diaryLimitRepository;

    LimitProducer limitProducer;

    @Value("${limit.value:0}")
    private BigDecimal diaryValue;

    public DiaryLimitService(DiaryLimitRepository diaryLimitRepository, LimitProducer limitProducer) {
        this.diaryLimitRepository = diaryLimitRepository;
        this.limitProducer = limitProducer;
    }


    public Optional<DiaryLimit> getDiaryLimit(final Long agency, final Long account) {
        final Optional<DiaryLimit> diaryLimit = diaryLimitRepository.findByAgencyAndAccount(agency, account);

        if(diaryLimit.isEmpty()) {
            var limit = new DiaryLimit();
            limit.setValue(diaryValue);
            limit.setAccount(account);
            limit.setAgency(agency);
            limit.setDate(LocalDateTime.now());

            return Optional.of(diaryLimitRepository.save(limit));
        }

        return diaryLimit;
    }

    public Optional<DiaryLimit> findById(Long id) {
        return diaryLimitRepository.findById(id);
    }

    public void validateDiaryLimit(TransactionDto transactionDto) {
        var diaryLimit = diaryLimitRepository.findByAgencyAndAccountAndDate(
                transactionDto.getAccount().getAgencyCode(), transactionDto.getAccount().getAccountCode(),
                LocalDateTime.now()
        );

        if(Objects.isNull(diaryLimit)) {
            diaryLimit =  new DiaryLimit();
            diaryLimit.setAgency(transactionDto.getAccount().getAgencyCode());
            diaryLimit.setAccount(transactionDto.getAccount().getAccountCode());
            diaryLimit.setValue(diaryValue);
            diaryLimit.setDate(transactionDto.getDate());

            diaryLimit = diaryLimitRepository.save(diaryLimit);
        }

        if(diaryLimit.getValue().compareTo(transactionDto.getValue()) < 0) {
            transactionDto.fraudSuspect();
            log.info("Transaction value is greater than diary limit. {}", transactionDto);
        } else if (diaryLimit.getValue().compareTo(BigDecimal.valueOf(10000l)) > 0) {
            transactionDto.humanAnalysing();
            log.info("Analyzing transaction {}", transactionDto);
        } else {
            transactionDto.analysed();
            log.info("Transaction Analysed");
            diaryLimit.setValue(diaryLimit.getValue().subtract(transactionDto.getValue()));
            diaryLimitRepository.save(diaryLimit);
        }

        limitProducer.sendMessage(transactionDto);
    }

}
