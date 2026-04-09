package com.enterprise.automation.framework.utils;

import java.util.Optional;

public final class TestDataReader {

    private static final String TEST_DATA_FILE = "testdata/test-data.properties";

    private TestDataReader() {
    }

    public static String get(String key) {
        return PropertyUtils.getProperty(TEST_DATA_FILE, key)
                .orElseThrow(() -> new IllegalStateException("Missing test data key: " + key));
    }

    public static Optional<String> find(String key) {
        return PropertyUtils.getProperty(TEST_DATA_FILE, key);
    }
}
