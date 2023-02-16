package com.beneti.transactionsvc.validator.impl;

import com.beneti.transactionsvc.dto.RequestTransactionDto;
import com.beneti.transactionsvc.exepctions.DomainBusinessException;
import com.beneti.transactionsvc.validator.TransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConditionalOnProperty(
        value = "{transactional.validation.base}",
        havingValue = "true",
        matchIfMissing = false
)
public class BaseTransactionValidator implements TransactionValidator {

    public static final int BANK_CODE = 785;

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {

        if(Objects.isNull(requestTransactionDto.getBeneficiary())) {
            throw new DomainBusinessException("Beneficiary data invalid!");
        } else if(Objects.isNull(requestTransactionDto.getBeneficiary().getBankCode()) ||
        requestTransactionDto.getBeneficiary().getBankCode().compareTo((long) BANK_CODE) != 785) {
            throw new DomainBusinessException("Beneficiary data invalid!");
        }

    }
}
