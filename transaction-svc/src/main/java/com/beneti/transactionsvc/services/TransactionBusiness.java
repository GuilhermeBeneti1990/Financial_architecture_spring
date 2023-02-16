package com.beneti.transactionsvc.services;

import com.beneti.transactionsvc.dto.RequestTransactionDto;
import com.beneti.transactionsvc.dto.TransactionDto;
import com.beneti.transactionsvc.exepctions.DomainBusinessException;
import com.beneti.transactionsvc.repositories.TransactionRepository;
import com.beneti.transactionsvc.validator.impl.TransactionValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TransactionBusiness {

    private TransactionRepository transactionRepository;
    private TransactionValidation transactionValidation;

    public TransactionBusiness(TransactionRepository transactionRepository, TransactionValidation transactionValidation) {
        this.transactionRepository = transactionRepository;
        this.transactionValidation = transactionValidation;
    }

    public void save(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        if(Objects.isNull(requestTransactionDto.getDate())) {
            requestTransactionDto.setDate(LocalDateTime.now());
        }

        transactionValidation.validate(requestTransactionDto);
        transactionRepository.save(requestTransactionDto);
    }

    public void updateTransaction(TransactionDto transactionDto) {
        log.info("Transaction updating: {}", transactionDto);
        transactionRepository.save(transactionDto);
    }

    public void approveTransaction(TransactionDto transactionEvent) {
        var transaction = getTransaction(transactionEvent);
        if(transaction.isPresent()) {
            var transactionDB = transaction.get();
            if(!transactionDB.isAnalysed() && transactionEvent.isAnalysed()) {
                transactionDB.approve();
                updateTransaction(transactionDB);
                log.info("Transaction approved: {}", transactionDB);
            }
        }
    }

    public Optional<TransactionDto> getTransaction(TransactionDto transactionDto) {
        return transactionRepository.findById(transactionDto.getUuid());
    }

}
