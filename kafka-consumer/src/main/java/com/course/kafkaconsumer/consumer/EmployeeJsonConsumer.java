package com.course.kafkaconsumer.consumer;

import com.course.kafkaconsumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJsonConsumer {
    private static final Logger log = LoggerFactory.getLogger(EmployeeJsonConsumer.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = "t_employee")
    public void consume(String message) throws JsonProcessingException {
        var employee = objectMapper.readValue(message, Employee.class);
        log.info("Employee: " + employee);
    }
}
