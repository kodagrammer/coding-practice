package com.spb.practice.springbootpractice.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
public class SampleComment {
    private Double postId;
    private Double id;
    private String name;
    private String email;
    private String body;
}
