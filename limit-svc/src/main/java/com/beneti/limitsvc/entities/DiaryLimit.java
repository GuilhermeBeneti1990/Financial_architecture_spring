package com.beneti.limitsvc.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    private LocalDateTime date;

}
