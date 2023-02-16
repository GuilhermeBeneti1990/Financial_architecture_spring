package com.beneti.transactionbff.services;

import com.beneti.transactionbff.dto.RequestTransactionDto;
import com.beneti.transactionbff.dto.TransactionDto;
import com.beneti.transactionbff.redis.TransactionRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {

    private TransactionRedisRepository transactionRedisRepository;

    private RetryTemplate retryTemplate;

    private ReactiveKafkaProducerTemplate<String, RequestTransactionDto> reactiveKafkaProducerTemplate;

    @Value("${app.topic}")
    private String topic;

    public TransactionService(TransactionRedisRepository transactionRedisRepository, RetryTemplate retryTemplate,
                              ReactiveKafkaProducerTemplate<String, RequestTransactionDto> reactiveKafkaProducerTemplate) {
        this.transactionRedisRepository = transactionRedisRepository;
        this.retryTemplate = retryTemplate;
        this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
    }

    @Transactional
    public Optional<TransactionDto> save(final RequestTransactionDto requestTransactionDto) {
        requestTransactionDto.setDate(LocalDateTime.now());
        reactiveKafkaProducerTemplate.send(topic, requestTransactionDto)
                .doOnSuccess(voidSenderResult -> log.info(voidSenderResult.toString()));
        return Optional.of(transactionRedisRepository.save(requestTransactionDto));
    }

//    @Retryable(value = QueryTimeoutException.class, maxAttempts = 5, backoff = @Backoff(delay = 100))
//    public Optional<TransactionDto> findById(final String id) {
//        log.info("Searching on Redis");
//        return transactionRedisRepository.findById(id);
//    }

    public Optional<TransactionDto> findById(final String id) {
        return retryTemplate.execute(ret -> {
            log.info("Searching on Redis");
            return transactionRedisRepository.findById(id);
        });
    }

}
