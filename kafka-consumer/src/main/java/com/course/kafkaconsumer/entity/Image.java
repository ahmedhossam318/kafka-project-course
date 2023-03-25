package com.course.kafkaconsumer.entity;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Image {
    private String name;
    private long size;
    private String type;
}
