package com.beneti.limitsvc.events;

import com.beneti.limitsvc.dto.TransactionDto;
import com.beneti.limitsvc.services.DiaryLimitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class LimitConsumer {

    private DiaryLimitService diaryLimitService;

    private ObjectMapper objectMapper;

    public LimitConsumer(DiaryLimitService diaryLimitService, ObjectMapper objectMapper) {
        this.diaryLimitService = diaryLimitService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${app.topic}")
    public void onConsume(final String message) {
        try {
            TransactionDto transactionDto = getTransaction(message);
            diaryLimitService.validateDiaryLimit(transactionDto);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private TransactionDto getTransaction(String message) throws IOException {
        TransactionDto transactionDto = objectMapper.readValue(message, TransactionDto.class);
        transactionDto.setDate(LocalDateTime.now());

        return transactionDto;
    }

}
