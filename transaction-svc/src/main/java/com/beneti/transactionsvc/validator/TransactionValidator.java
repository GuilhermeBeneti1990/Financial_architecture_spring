package com.beneti.transactionsvc.validator;

import com.beneti.transactionsvc.dto.RequestTransactionDto;
import com.beneti.transactionsvc.exepctions.DomainBusinessException;

@FunctionalInterface
public interface TransactionValidator {

    void validate(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException;

}
