package com.enterprise.automation.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * Utility class for reading property values from files available in the
 * classpath.
 *
 * <p>
 * This class loads Java properties files from src/test/resources or
 * src/main/resources
 * and returns requested keys in a safe Optional form.
 * </p>
 */
public final class PropertyUtils {

    private PropertyUtils() {
        // Prevent instantiation
    }

    /**
     * Returns the property value for the given key from the provided resource file.
     *
     * @param resourcePath classpath-relative resource path
     * @param key          property key
     * @return Optional containing property value if found
     */
    public static Optional<String> getProperty(String resourcePath, String key) {
        Properties properties = loadProperties(resourcePath);
        return Optional.ofNullable(properties.getProperty(key))
                .map(String::trim)
                .filter(value -> !value.isEmpty());
    }

    /**
     * Loads all properties from the provided resource file.
     *
     * @param resourcePath classpath-relative resource path
     * @return loaded Properties object
     */
    private static Properties loadProperties(String resourcePath) {
        Properties properties = new Properties();

        try (InputStream inputStream = PropertyUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalStateException("Unable to find resource: " + resourcePath);
            }

            properties.load(inputStream);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load resource: " + resourcePath, exception);
        }
    }
}