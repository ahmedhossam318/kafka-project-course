package com.course.kafkaconsumer.error.handler;

import org.springframework.stereotype.Service;


@Service
public class RetryErrorHandler extends RuntimeException {

    public void handle() {
        System.out.println("There is another trial");
    }
}
