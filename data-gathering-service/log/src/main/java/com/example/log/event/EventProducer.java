package com.example.log.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {
    private final KafkaTemplate kafkaTemplate;

    public EventProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String payload) {
        kafkaTemplate.send(topic, payload);
    }
}
