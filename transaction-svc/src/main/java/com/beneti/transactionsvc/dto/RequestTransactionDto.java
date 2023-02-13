package com.beneti.transactionsvc.dto;

import com.beneti.transactionsvc.enums.SituationEnum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RequestTransactionDto extends TransactionDto{

    @JsonIgnore
    private LocalDateTime date;

    @JsonIgnore
    private SituationEnum situationEnum;

}