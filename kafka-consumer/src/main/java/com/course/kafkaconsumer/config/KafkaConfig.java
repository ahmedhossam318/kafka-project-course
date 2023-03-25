package com.course.kafkaconsumer.config;


import com.course.kafkaconsumer.entity.CarLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;


    @Bean
    ConsumerFactory<Object, Object> consumerFactory() {
        var properties = kafkaProperties.buildConsumerProperties();
        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");

        return new DefaultKafkaConsumerFactory<Object, Object>(properties);
    }

    @Bean(name = "farLocationContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> farLocationContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());
        factory.setRecordFilterStrategy(new RecordFilterStrategy<>() {
            ObjectMapper objectMapper = new ObjectMapper();

            @Override
            public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
                try {
                    var carLocation = objectMapper.readValue(consumerRecord.value().toString(), CarLocation.class);

                    return carLocation.getDistance() <= 100;
                } catch (Exception e) {
                    return false;
                }
            }
        });
        return factory;
    }

    @Bean(value = "invoiceDltContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> invoiceDltContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer
            , KafkaOperations<Object, Object> kafkaTemplate
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());
        var recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
                (record, ex) -> new TopicPartition("t_invoice_dlt", record.partition()));
        factory.setCommonErrorHandler(new DefaultErrorHandler(recoverer, new FixedBackOff(1000, 5)));

        return factory;
    }

//    @Bean
//    public RetryTopicConfiguration myOtherRetryableTopic(KafkaTemplate<String, Image> template) {
//
//        return RetryTopicConfigurationBuilder
//                .newInstance()
//                .exponentialBackoff(6000, 2, 50000)
//                .maxAttempts(3)
//                .excludeTopics(Collections.singletonList("t_image"))
//                .retryOn(RetryErrorHandler.class)
//                .create(template);
//    }


}
