package com.beneti.transactionbff.exceptions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ErrorResponse {

    private String message;

    private Integer code;

}
