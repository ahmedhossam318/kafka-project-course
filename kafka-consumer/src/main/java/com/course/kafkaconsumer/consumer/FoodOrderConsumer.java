package com.course.kafkaconsumer.consumer;


import com.course.kafkaproducer.entity.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderConsumer {
    private static final Logger log = LoggerFactory.getLogger(FoodOrderConsumer.class);
    private static final int MAX_AMOUnT_ORDER = 7;
    private ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "t_food_order")
    public void consume(String foodOrder) throws JsonProcessingException {
        var order = mapper.readValue(foodOrder, FoodOrder.class);
        if (order.getAmount() > MAX_AMOUnT_ORDER) {
            throw new IllegalArgumentException("Food order amount is too many");
        }

        log.info("food order valid : {}", foodOrder);
    }
}
