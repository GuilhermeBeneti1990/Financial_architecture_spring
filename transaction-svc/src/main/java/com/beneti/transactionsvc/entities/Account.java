package com.beneti.transactionsvc.entities;

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

    @NotNull(message = "Agency code must be informed")
    private Long agencyCode;

    @NotNull(message = "Account code must be informed")
    private Long accountCode;

}
