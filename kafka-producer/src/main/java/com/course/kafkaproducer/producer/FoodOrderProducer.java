package com.course.kafkaproducer.producer;


import com.course.kafkaproducer.entity.FoodOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void send(FoodOrder foodOrder) {
        try {
            var json = objectMapper.writeValueAsString(foodOrder);
            kafkaTemplate.send("t_food_order", json);
            System.out.println(json);
        } catch (Exception e) {
        }
    }
}
