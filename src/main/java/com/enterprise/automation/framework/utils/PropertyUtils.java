package com.enterprise.automation.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Utility class for reading property values from files available in the
 * classpath.
 *
 * <p>
 * This class loads Java properties files from src/test/resources or
 * src/main/resources
 * and returns requested keys in a safe Optional form.
 * </p>
 *
 * <p>
 * Each file is loaded at most once per JVM run; subsequent calls return the
 * cached Properties instance.
 * </p>
 */
public final class PropertyUtils {

    private static final Map<String, Properties> CACHE = new ConcurrentHashMap<>();

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
     * Returns a cached Properties instance for the given resource path, loading
     * from the classpath on first access.
     *
     * @param resourcePath classpath-relative resource path
     * @return cached Properties object
     */
    private static Properties loadProperties(String resourcePath) {
        return CACHE.computeIfAbsent(resourcePath, PropertyUtils::loadFromClasspath);
    }

    /**
     * Loads properties from the classpath. Called at most once per resource path.
     *
     * @param resourcePath classpath-relative resource path
     * @return loaded Properties object
     */
    private static Properties loadFromClasspath(String resourcePath) {
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
