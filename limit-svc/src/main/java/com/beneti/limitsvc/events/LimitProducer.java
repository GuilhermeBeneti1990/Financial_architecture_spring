package com.beneti.limitsvc.events;

import com.beneti.limitsvc.dto.TransactionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LimitProducer {

    @Value("${app.returnTopic}")
    private String topic;

    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    public LimitProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(final TransactionDto transactionDto) {
        try {
            String payload = objectMapper.writeValueAsString(transactionDto);

            Message<String> message = MessageBuilder
                    .withPayload(payload)
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .setHeader(KafkaHeaders.PARTITION_ID, 0)
                    .build();

            kafkaTemplate.send(message);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

}
