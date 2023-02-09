package com.beneti.transactionbff.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BeneficiaryDto implements Serializable {

    private static final long serialVersionUID = 2806412403585360625L;

    @Schema(description = "Document of beneficiary (CPF)")
    @NotNull(message = "CPF must be informed")
    private Long CPF;

    @Schema(description = "Bank of destiny code")
    @NotNull(message = "Bank's code must be informed.")
    private Long bankCode;

    @Schema(description = "Destiny agency")
    @NotNull(message = "Agency must be informed")
    private String agency;

    @Schema(description = "Destiny account")
    @NotNull(message = "Destiny account must be informed")
    private String account;

    @Schema(description = "Favored's name")
    @NotNull(message = "Favored's name must be informed")
    private String favoredName;

}
