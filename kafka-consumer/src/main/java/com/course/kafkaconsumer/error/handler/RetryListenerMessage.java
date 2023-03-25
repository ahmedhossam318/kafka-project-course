package com.course.kafkaconsumer.error.handler;


import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
public class RetryListenerMessage implements RetryListener {
    @Override
    public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
        System.out.println(String.format("Error during retry %d operation:", retryContext.getRetryCount()));

        return false;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {

    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
        RetryTemplate retryTemplate = new RetryTemplate();

        try {
            retryTemplate.execute(new RetryCallback<Object, Exception>() {

                @Override
                public Object doWithRetry(RetryContext context) throws Exception {
                    // This code will be retried if it throws an exception

                    // Get the current retry count
                    int currentRetryCount = context.getRetryCount();

                    // Do some work that may fail
                    if (currentRetryCount < 2) {
                        System.out.println("Throwing exception to simulate failure");
                        throw new Exception("Simulated failure");
                    } else {
                        System.out.println("Operation succeeded on retry " + (currentRetryCount + 1));
                        return "Success";
                    }
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
            retryCallback.doWithRetry(retryContext);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        System.out.println(String.format("Error during retry %d operation: %s", retryContext.getRetryCount(), throwable.getMessage()));

    }
}

