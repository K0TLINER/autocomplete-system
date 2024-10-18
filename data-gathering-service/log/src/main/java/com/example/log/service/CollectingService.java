package com.example.log.service;

import com.example.log.adapter.FileAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectingService {
    @Value("${my.query.log.location}")
    private String logLocation;
    @Value("${my.query.log.suffix}")
    private String logSuffix;
    private final FileAdapter fileAdapter;

    public CollectingService(FileAdapter fileAdapter) {
        this.fileAdapter = fileAdapter;
    }

    private String getPreviousLogFileName() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return logLocation + yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + logSuffix;
    }

    public Map<String, Integer> collectQueries() {
        List<String> queries = fileAdapter.readAll(getPreviousLogFileName());
        Map<String, Integer> data = new HashMap<>();
        final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
        for (String query : queries) {
            String _query = query.substring(TIMESTAMP_FORMAT.length() + 1);
            _query = _query.toLowerCase();
            if(data.containsKey(_query)) data.put(_query, data.get(_query) + 1);
            else data.put(_query, 1);
        }
        return data;
    }
}
