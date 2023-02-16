package com.beneti.transactionbff.config;

import com.beneti.transactionbff.dto.RequestTransactionDto;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

import java.util.Map;

@Configuration
public class ReactiveKafkaProducerConfig {

    @Bean
    public ReactiveKafkaProducerTemplate<String, RequestTransactionDto> reactiveKafkaProducerTemplate(final KafkaProperties kafkaProperties) {
        Map<String, Object> properties = kafkaProperties.buildProducerProperties();
        return new ReactiveKafkaProducerTemplate<String, RequestTransactionDto>(SenderOptions.create(properties));
    }

}
