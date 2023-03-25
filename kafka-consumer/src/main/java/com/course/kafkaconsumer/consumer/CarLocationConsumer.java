package com.course.kafkaconsumer.consumer;


import com.course.kafkaconsumer.entity.CarLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarLocationConsumer {

    ObjectMapper objectMapper = new ObjectMapper();


    @KafkaListener(topics = "t_location", groupId = "cg-all-location")
    public void listenAll(String message) {
        try {
            CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
            log.info("listenAll : {}", carLocation);
        } catch (Exception e) {
            log.error("CarLocationConsumer receive error: {}", e.getMessage());
        }

    }

    @KafkaListener(topics = "t_location", groupId = "cg-far-location", containerFactory = "farLocationContainerFactory")
    public void listenFar(String message) {
        try {
            CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
            log.info("listenFar : {}", carLocation);
        } catch (Exception e) {
            log.error("CarLocationConsumer receive error: {}", e.getMessage());
        }

    }
}
