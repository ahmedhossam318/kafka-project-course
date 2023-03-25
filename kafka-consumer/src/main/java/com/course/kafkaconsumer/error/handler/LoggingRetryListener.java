package com.course.kafkaconsumer.error.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.stereotype.Component;


@Component
public class LoggingRetryListener extends RetryListenerSupport {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        logger.error("Retry failed after {} attempts: {}", context.getRetryCount(), throwable.getMessage());
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        logger.info("Retry completed after {} attempts", context.getRetryCount());
    }
}
