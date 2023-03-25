package com.course.kafkaconsumer.consumer;


import com.course.kafkaproducer.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ImageConsumer {
    private static final Logger log = LoggerFactory.getLogger(ImageConsumer.class);
    private ObjectMapper mapper = new ObjectMapper();
    //, errorHandler = "globalErrorHandler"

    //    @RetryableTopic(
//            attempts = "3",
//            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
//            backoff = @Backoff(delay = 4000, multiplier = 2.0),
//            exclude = {RetryErrorHandler.class}
//
//    )
    @KafkaListener(topics = "t_image")
    public void consume(String message) throws JsonProcessingException {
        var image = mapper.readValue(message, Image.class);
        if (image.getType().equalsIgnoreCase("svg")) {
            throw new IllegalArgumentException("simulate faild API call ");
        }

        log.info("processing Image : {}", image);
    }
}
