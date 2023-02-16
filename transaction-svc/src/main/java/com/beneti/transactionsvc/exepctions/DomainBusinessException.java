package com.beneti.transactionsvc.exepctions;

public class DomainBusinessException extends Exception {
    public DomainBusinessException(String message) {
        super(message);
    }
}
