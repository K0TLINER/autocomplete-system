package com.example.log.service;

import com.example.log.adapter.FileAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class LoggingServiceTests {
    @Mock
    private FileAdapter fileAdapter;
    @InjectMocks
    private LoggingService loggingService;
    @Test
    public void testLogQuery() {
        String message = "This is a TEST log";

        loggingService.logQuery(message);

//        verify(fileAdapter).write(message);
    }
}
