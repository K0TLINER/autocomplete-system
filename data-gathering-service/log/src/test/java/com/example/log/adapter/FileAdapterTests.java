package com.example.log.adapter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileAdapterTests {
    // timestamp regex
//    private static final String LOG_PATTERN = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} .+";
//    private static final Pattern pattern = Pattern.compile(LOG_PATTERN);
    private static final String INPUT_TEST_FILENAME = "input.log";
    private static final String OUTPUT_TEST_FILENAME = "output.log";
    private final FileAdapter fileAdapter = new FileAdapter();
    @AfterEach
    public void cleanUp() {
        File file = new File(INPUT_TEST_FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }
    @Test
    public void test_write() throws IOException{
        String content = "test content";

        fileAdapter.write(INPUT_TEST_FILENAME, content);

        File file = new File(INPUT_TEST_FILENAME);
        assertTrue(file.exists(), "File should exist after writing");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            assertTrue(line.contains(content));
        }
    }
    @Test
    public void test_readAll() {
        String filename = getClass().getClassLoader().getResource(OUTPUT_TEST_FILENAME).getPath();

        List<String> compareStrings = List.of("ABC", "ABC DEF", "AB DE FG");
        List<String> queries = fileAdapter.readAll(filename);

        assertTrue(compareStrings.equals(queries));
    }
}
