package com.example.log.event;

import com.example.log.service.LoggingService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumer {
    private final LoggingService loggingService;

    public EventConsumer(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    private Data getObjectFromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Data.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @KafkaListener(topics = "${my.kafka.topic.logging}", groupId = "test-group")
    public void consume(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
//        Data data = new Data();
//        data.query = "ABC DEF";
//
//        String x = objectMapper.writeValueAsString(data);
//        System.out.println(x);
        System.out.println(json);
        String query = this.getObjectFromJson(json).query;

        loggingService.logQuery(query);
    }

    @Getter
    public static class Data {
        private String query;
    }
}
