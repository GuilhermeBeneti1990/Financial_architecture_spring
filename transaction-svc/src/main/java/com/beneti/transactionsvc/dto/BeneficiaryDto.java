package com.beneti.transactionsvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class BeneficiaryDto implements Serializable {

    private static final long serialVersionUID = 2806412403585360625L;

    @NotNull(message = "CPF must be informed")
    private Long CPF;

    @NotNull(message = "Bank's code must be informed.")
    private Long bankCode;

    @NotNull(message = "Agency must be informed")
    private String agency;

    @NotNull(message = "Destiny account must be informed")
    private String account;

    @NotNull(message = "Favored's name must be informed")
    private String favoredName;

}