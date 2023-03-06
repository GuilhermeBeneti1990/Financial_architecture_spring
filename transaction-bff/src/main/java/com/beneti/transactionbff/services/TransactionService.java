package com.beneti.transactionbff.services;

import com.beneti.transactionbff.dto.RequestTransactionDto;
import com.beneti.transactionbff.dto.TransactionDto;
import com.beneti.transactionbff.enums.SituationEnum;
import com.beneti.transactionbff.exceptions.NotFoundException;
import com.beneti.transactionbff.redis.TransactionRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

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
    @Retryable(value = QueryTimeoutException.class, maxAttempts = 5, backoff = @Backoff(delay = 100))
    public Mono<RequestTransactionDto> save(final RequestTransactionDto requestTransactionDto) {
        return Mono.fromCallable(() -> {
            requestTransactionDto.setDate(LocalDateTime.now());
            requestTransactionDto.setSituationEnum(SituationEnum.NOT_ANALISED);
            return transactionRedisRepository.save(requestTransactionDto);
        }).doOnError(throwable -> {
                    log.error(throwable.getMessage(), throwable);
                    throw new NotFoundException("Unable to find resource");
                })
                .doOnSuccess(requestTransactionDto1 -> {
                    log.info("Transaction sended with successfully - {}", requestTransactionDto1);
                    reactiveKafkaProducerTemplate.send(topic, requestTransactionDto)
                            .doOnSuccess(voidSenderResult -> log.info(voidSenderResult.toString()))
                            .subscribe();
                })
                .doFinally(signalType -> {
                    if(signalType.compareTo(SignalType.ON_COMPLETE) == 0) {
                        log.info("Message sended to kafka: {}", requestTransactionDto);
                    }
                });
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
