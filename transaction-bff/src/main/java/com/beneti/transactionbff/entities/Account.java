package com.beneti.transactionbff.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class Account implements Serializable {

    private static final long serialVersionUID = 2806412403585360625L;

    @Schema(description = "Agency code")
    @NotNull(message = "Agency code must be informed")
    private Long agencyCode;

    @Schema(description = "Account code")
    @NotNull(message = "Account code must be informed")
    private Long accountCode;

}
