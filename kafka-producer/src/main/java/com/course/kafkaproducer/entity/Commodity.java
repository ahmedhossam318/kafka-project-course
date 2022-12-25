package com.course.kafkaproducer.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@ToString
public class Commodity {
    private String name ;
    private double price ;
    private String measurement;
    private long timestamp;

    public Commodity(String name, double price, String measurement, long timestamp) {
        this.name = name;
        setPrice(price);
        this.measurement = measurement;
        this.timestamp = timestamp;
    }

    public void setPrice(double price) {
        this.price = Math.round(price * 100d) / 100d;
    }
}
