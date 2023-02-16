package com.beneti.limitsvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class BeneficiaryDto implements Serializable {

    private static final long serialVersionUID = 2806412403585360625L;
    private Long CPF;

    private Long bankCode;

    private String agency;

    private String account;

    private String favoredName;

}
