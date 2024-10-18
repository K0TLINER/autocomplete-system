package com.example.log.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {
    @KafkaListener(topics = "${my.kafka.topic.logging}")
    public void consume(String message) {
        System.out.println(message);
    }
}
