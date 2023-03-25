package com.course.kafkaconsumer.consumer;

import com.course.kafkaproducer.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InvoiceConsumer {

    private ObjectMapper mapper = new ObjectMapper();
    //, errorHandler = "globalErrorHandler"

    //    @RetryableTopic(
//            attempts = "3",
//            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
//            backoff = @Backoff(delay = 4000, multiplier = 2.0),
//            exclude = {RetryErrorHandler.class}
//
//    )
    @KafkaListener(topics = "t_invoice", containerFactory = "invoiceDltContainerFactory")
    public void consume(String message) throws JsonProcessingException {
        var invoice = mapper.readValue(message, Invoice.class);
        if (invoice.getAmount() < 1) {
            throw new IllegalArgumentException(" Invalid amount : " + invoice.getAmount() + "for Invoice :  " + invoice.getNumber());
        }

        log.info("processing Invoice : {}", invoice);
    }


}
