package com.beneti.limitsvc.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Account implements Serializable {

    private static final long serialVersionUID = 2806412403585360625L;

    private Long agencyCode;

    private Long accountCode;

}
