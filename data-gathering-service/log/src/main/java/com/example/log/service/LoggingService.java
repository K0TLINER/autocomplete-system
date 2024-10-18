package com.example.log.service;

import com.example.log.adapter.FileAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LoggingService {
    @Value("${my.query.log.location}")
    private String logLocation;
    @Value("${my.query.log.suffix}")
    private String logSuffix;
    private final FileAdapter fileAdapter;

    public LoggingService(FileAdapter fileAdapter) {
        this.fileAdapter = fileAdapter;
    }

    private String getCurrentLogFileName() {
        LocalDate today = LocalDate.now();
        return logLocation + today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + logSuffix;
    }

    private String queryWithTimestamp(String query) {
        LocalDateTime now = LocalDateTime.now();

    }

    public void logQuery(String query) {
        fileAdapter.write(getCurrentLogFileName(), query);
    }
}
