package com.beneti.transactionbff.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class DiaryLimit {

    private Long id;

    private Long agency;

    private Long account;

    private BigDecimal value;

}
