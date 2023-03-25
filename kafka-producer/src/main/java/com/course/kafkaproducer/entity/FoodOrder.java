package com.course.kafkaproducer.entity;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodOrder {
    private int amount;
    private String item;
}
