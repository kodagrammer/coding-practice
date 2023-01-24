package com.spb.practice.springbootpractice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Setter(AccessLevel.NONE)
public class JsonTestData {
    private String idx;
    private String name;
    private double mathScore;
    private Double englishScore;
    private BigDecimal avr;
}