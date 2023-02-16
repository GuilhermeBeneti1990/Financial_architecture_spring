package com.beneti.transactionsvc.validator.impl;

import com.beneti.transactionsvc.dto.RequestTransactionDto;
import com.beneti.transactionsvc.exepctions.DomainBusinessException;
import com.beneti.transactionsvc.validator.impl.TransactionValidation;

public class EmpyTransactionValidationBean implements TransactionValidation {
    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {

    }
}
