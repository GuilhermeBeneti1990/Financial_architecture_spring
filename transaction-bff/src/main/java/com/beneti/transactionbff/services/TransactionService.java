package com.beneti.transactionbff.services;

import com.beneti.transactionbff.dto.RequestTransactionDto;
import com.beneti.transactionbff.dto.TransactionDto;
import com.beneti.transactionbff.redis.TransactionRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
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

    @Autowired
    private RetryTemplate retryTemplate;

    public TransactionService(TransactionRedisRepository transactionRedisRepository) {
        this.transactionRedisRepository = transactionRedisRepository;
    }

    @Transactional
    public Optional<TransactionDto> save(final RequestTransactionDto requestTransactionDto) {
        requestTransactionDto.setDate(LocalDateTime.now());
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
