package com.course.kafkaproducer.producer;


import com.course.kafkaproducer.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ImageProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    private Logger logger = LoggerFactory.getLogger(ImageProducer.class);


    private ObjectMapper objectMapper = new ObjectMapper();

    public void send(Image image) throws JsonProcessingException {
        try {

            var json = objectMapper.writeValueAsString(image);
            kafkaTemplate.send("t_image", image.getType(), json);

        } catch (Exception exception) {
            logger.warn("Error sending message");
        }
    }
}
