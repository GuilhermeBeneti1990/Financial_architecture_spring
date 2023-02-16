package com.beneti.transactionsvc.events;

import com.beneti.transactionsvc.dto.TransactionDto;
import com.beneti.transactionsvc.enums.SituationEnum;
import com.beneti.transactionsvc.services.TransactionBusiness;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class TransactionConsumer {

    private final ObjectMapper objectMapper;

    private final TransactionBusiness transactionBusiness;

    public TransactionConsumer(ObjectMapper objectMapper, TransactionBusiness transactionBusiness) {
        this.objectMapper = objectMapper;
        this.transactionBusiness = transactionBusiness;
    }

    @KafkaListener(topics = "${events.consumeTopic}")
    public void consumeTransaction(String message) {
        try {
            var transaction = getTransaction(message);
            transactionBusiness.updateTransaction(transaction);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @KafkaListener(topics = "${events.returnTopic}")
    public void consumeTransactionReturn(String message) {
        try {
            var transaction = getTransaction(message);
            log.info("Transaction received to be analysed: {}", transaction);
            if(!transaction.isAnalysed()) {
                return;
            } else {
                log.info("Transaction analysed: {}", transaction);
                transactionBusiness.approveTransaction(transaction);

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private TransactionDto getTransaction(String message) throws IOException {
        TransactionDto transactionDto = objectMapper.readValue(message, TransactionDto.class);
        if(Objects.isNull(transactionDto.getSituation())) {
            transactionDto.setSituation(SituationEnum.NOT_ANALYSED);
        }

        transactionDto.setDate(LocalDateTime.now());
        return transactionDto;
    }

}
