package com.beneti.transactionsvc.dto;

import com.beneti.transactionsvc.entities.Account;
import com.beneti.transactionsvc.enums.SituationEnum;
import com.beneti.transactionsvc.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
public class TransactionDto {

    @Id
    private UUID uuid;

    @NotNull(message = "Value must be informed")
    private BigDecimal value;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @NotNull(message = "Account must be informed")
    @Valid
    private Account account;

    @Valid
    private BeneficiaryDto beneficiary;

    @NotNull(message = "Transaction type must be informed")
    private TransactionType transaction;

    private SituationEnum situation;
}
