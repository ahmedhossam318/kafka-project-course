package com.course.kafkaconsumer.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CarLocation {
    @JsonProperty("car_id")
    private String carId;
    private long timestamp;
    private int distance;
}
