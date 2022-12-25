package com.course.kafkaconsumer.service;

import com.course.kafkaconsumer.entity.Commodity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CommodityNotificationService {
    private static final Logger log = LoggerFactory.getLogger(CommodityNotificationService.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "t_commodity",groupId = "cg-notification")
    public  void consume(String message ) throws JsonProcessingException {
        var commodity = objectMapper.readValue(message, Commodity.class);
        log.info("Notification logic for {}" ,commodity);
    }
}
