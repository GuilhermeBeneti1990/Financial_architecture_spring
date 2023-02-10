package com.beneti.transactionbff.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum SituationEnum {
    ANALISED,
    NOT_ANALISED,
    HUMAN_ANALISING,
    FRAUD_SUSPECT,
    CONFIRMED_RISK
}
