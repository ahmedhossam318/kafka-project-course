package com.course.kafkaproducer.producer;

import com.course.kafkaproducer.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InvoiceProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();


    public void send(Invoice invoice) throws JsonProcessingException {
        try {

            var json = objectMapper.writeValueAsString(invoice);
            kafkaTemplate.send("t_invoice", invoice.getNumber(), json);

        } catch (Exception exception) {
            log.warn("Error sending message");
        }
    }

}
