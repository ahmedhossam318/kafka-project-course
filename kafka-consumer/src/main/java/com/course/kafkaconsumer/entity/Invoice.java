package com.course.kafkaconsumer.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Invoice {
    private String number;
    private double amount;
    private String currency;
}
