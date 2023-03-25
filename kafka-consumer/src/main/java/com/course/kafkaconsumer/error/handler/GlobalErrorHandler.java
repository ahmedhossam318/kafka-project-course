package com.course.kafkaconsumer.error.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class GlobalErrorHandler implements KafkaListenerErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
        log.warn("Global Error Handler for message : {} ", message);
        handleException(e, message);

        return null;
    }


    private void handleException(Exception e, Message<?> message) {
        // Log the error or send it to a dead letter queue
//        try {
//            log.error("Global Error Handler");
//        } catch (Exception exception) {
//            System.err.printf("Error processing message: %s%n%s%n", message);
//        }
    }
}
