package com.beneti.transactionsvc.validator.impl;

import com.beneti.transactionsvc.dto.RequestTransactionDto;
import com.beneti.transactionsvc.exepctions.DomainBusinessException;

public interface TransactionValidation {

    void validate(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException;

}
