package com.example.log.service;

import com.example.log.adapter.FileAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CollectingServiceTests {
    @Autowired private CollectingService collectingService;
    @MockBean private FileAdapter fileAdapter;
    @Test
    public void test_collect_queries() {

        List<String> queries = List.of(
                "2023/11/22 12:03:56 apple",
                "2023/11/22 12:09:23 Black sheep",
                "2023/11/22 12:23:03 Apple",
                "2023/11/22 12:44:11 apple",
                "2023/11/22 19:04:01 one TWO three"
        );
        given(this.fileAdapter.readAll(any()))
                .willReturn(queries);

        Map<String, Integer> results = collectingService.collectQueries();

        assertTrue(results.get("apple") == 3);
        assertTrue(results.get("black sheep") == 1);
        assertTrue(results.get("one two three") == 1);

        assertNull(results.get(" "));
    }
}
