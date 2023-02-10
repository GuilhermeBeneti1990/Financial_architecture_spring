package com.beneti.limitsvc.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Data
@EqualsAndHashCode(of = "id")
public class DiaryLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agency;

    private Long account;

    private BigDecimal value;

}
