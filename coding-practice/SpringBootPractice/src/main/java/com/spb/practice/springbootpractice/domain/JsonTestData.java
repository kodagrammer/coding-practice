package com.spb.practice.springbootpractice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PACKAGE)
@Builder
public class JsonTestData {

    private Double id;
    private String name;
    private String phone;
    private String address;
    private String email;
}
