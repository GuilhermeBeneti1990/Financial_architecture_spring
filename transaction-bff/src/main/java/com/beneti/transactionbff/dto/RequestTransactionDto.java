package com.beneti.transactionbff.dto;

import com.beneti.transactionbff.enums.SituationEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(of = {"uuid", "situationEnum"})
@Schema(description = "Transaction object represents a transaction promise")
public class RequestTransactionDto extends TransactionDto{

    @JsonIgnore
    private LocalDateTime date;

    @JsonIgnore
    private SituationEnum situationEnum;

}
