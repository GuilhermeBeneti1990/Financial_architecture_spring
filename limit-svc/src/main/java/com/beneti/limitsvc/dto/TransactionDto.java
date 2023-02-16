package com.beneti.limitsvc.dto;

import com.beneti.limitsvc.entities.Account;
import com.beneti.limitsvc.enums.SituationEnum;
import com.beneti.limitsvc.enums.TransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
@ToString(of = {"uuid", "situation"})
public class TransactionDto {

    private UUID uuid;

    private BigDecimal value;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;


    private Account account;

    private BeneficiaryDto beneficiary;

    private TransactionTypeEnum transactionType;

    private SituationEnum situation;

    public void fraudSuspect() {
        situation = SituationEnum.FRAUD_SUSPECT;
    }

    public void analysed() {
        situation = SituationEnum.ANALYSED;
    }

    public void humanAnalysing() {
        situation = SituationEnum.HUMAN_ANALYSING;
    }

}
