package com.beneti.transactionsvc.validator.impl;

import com.beneti.transactionsvc.dto.RequestTransactionDto;
import com.beneti.transactionsvc.exepctions.DomainBusinessException;
import com.beneti.transactionsvc.validator.TransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(
        value = "${transactional.validation.time}",
        havingValue = "true",
        matchIfMissing = false
)
public class TimeTransactionValidator implements TransactionValidator {

    public static final int HOUR_END = 18;

    @Override
    public void validate(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        if(LocalDateTime.now().getHour() > HOUR_END || requestTransactionDto.getDate().getHour() > HOUR_END) {
            throw new DomainBusinessException("Unable before 18:00");
        }
    }
}
