package com.beneti.transactionbff.dto;

import com.beneti.transactionbff.entities.Account;
import com.beneti.transactionbff.enums.SituationEnum;
import com.beneti.transactionbff.enums.TransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
@RedisHash(value = "TransactionDto", timeToLive = 300)
public class TransactionDto {

    @Schema(description = "Unique code to identify each transaction")
    @Id
    private UUID uuid;

    @Schema(description = "Transaction value")
    @NotNull(message = "Value must be informed")
    private BigDecimal value;

    @Schema(description = "Transaction date (YYYY-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @Schema(description = "Account of transaction")
    @NotNull(message = "Account must be informed")
    @Valid
    private Account account;

    @Schema(description = "Transaction beneficiary")
    @Valid
    private BeneficiaryDto beneficiary;

    @Schema(description = "Transaction type")
    @NotNull(message = "Transaction type must be informed")
    private TransactionTypeEnum transactionType;

    @Schema(description = "Transaction situation")
    private SituationEnum situation;
}
