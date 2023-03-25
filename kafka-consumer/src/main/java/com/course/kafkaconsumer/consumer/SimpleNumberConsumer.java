package com.course.kafkaconsumer.consumer;


import com.course.kafkaproducer.entity.SimpleNumber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SimpleNumberConsumer {
    private static final Logger log = LoggerFactory.getLogger(SimpleNumberConsumer.class);
    private ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "t_simple_number", errorHandler = "globalErrorHandler")
    public void consume(String message) throws JsonProcessingException {
        var number = mapper.readValue(message, SimpleNumber.class);
        if (number.getNumber() % 2 != 0) {
            throw new IllegalArgumentException("odd number");
        }

        log.info("Valid Number : {}", number);
    }
}
