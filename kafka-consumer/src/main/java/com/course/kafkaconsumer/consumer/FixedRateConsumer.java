package com.course.kafkaconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class FixedRateConsumer {
    private static final Logger log = LoggerFactory.getLogger(FixedRateConsumer.class);

    @KafkaListener(topics = "fixedrate2")
    public void consume(String message)
    {
        log.info("Consume : {}" , message);
    }

}

