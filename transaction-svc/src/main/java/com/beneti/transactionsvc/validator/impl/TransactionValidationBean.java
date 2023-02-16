package com.beneti.transactionsvc.validator.impl;

import com.beneti.transactionsvc.dto.RequestTransactionDto;
import com.beneti.transactionsvc.exepctions.DomainBusinessException;
import com.beneti.transactionsvc.validator.TransactionValidator;
import com.beneti.transactionsvc.validator.impl.BaseTransactionValidator;
import com.beneti.transactionsvc.validator.impl.TimeTransactionValidator;
import com.beneti.transactionsvc.validator.impl.TransactionValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnBean(value = {BaseTransactionValidator.class, TimeTransactionValidator.class})
@ConditionalOnExpression("${transaction.validation.enabled}")
@Slf4j
public class TransactionValidationBean implements TransactionValidation {

    private List<TransactionValidator> transactionValidatorList = new ArrayList<>();

    @PostConstruct
    public void addBeans() {
        transactionValidatorList.add(new BaseTransactionValidator());
        transactionValidatorList.add(new TimeTransactionValidator());
    }

    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {

        transactionValidatorList.stream().forEach(transactionValidator -> {
            try {
                transactionValidator.validate(requestTransactionDto);
            } catch (DomainBusinessException e) {
                log.error(e.getMessage());
            }
        });

    }

}
