package com.enterprise.automation.framework.config;

import com.enterprise.automation.framework.utils.PropertyUtils;

/**
 * Central configuration class for framework-level properties.
 *
 * <p>
 * Reads values from:
 * 1. JVM system properties (highest priority)
 * 2. framework-config.properties (fallback)
 *
 * <p>
 * This allows flexible execution like:
 * mvn clean test -Dbrowser=firefox -Dheadless=true
 */
public final class FrameworkConfig {

    private static final String CONFIG_FILE = "config/framework-config.properties";

    private FrameworkConfig() {
        // Prevent instantiation
    }

    /**
     * Returns the application base URL.
     *
     * @return base URL
     */
    public static String getBaseUrl() {
        return getRequiredProperty("base.url");
    }

    /**
     * Returns the browser name.
     *
     * @return browser name
     */
    public static String getBrowser() {
        return getRequiredProperty("browser");
    }

    /**
     * Returns explicit wait timeout in seconds.
     *
     * @return explicit wait timeout
     */
    public static long getExplicitWaitSeconds() {
        return Long.parseLong(getRequiredProperty("explicit.wait.seconds"));
    }

    /**
     * Returns page load timeout in seconds.
     *
     * @return page load timeout
     */
    public static long getPageLoadTimeoutSeconds() {
        return Long.parseLong(getRequiredProperty("page.load.timeout.seconds"));
    }

    /**
     * Returns whether execution should run in headless mode.
     *
     * @return true if headless mode is enabled, otherwise false
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getPropertyWithOverride("headless").orElse("false"));
    }

    /**
     * Gets a required property value.
     *
     * @param key property key
     * @return property value
     * @throws IllegalStateException if property is missing
     */
    private static String getRequiredProperty(String key) {
        return getPropertyWithOverride(key)
                .orElseThrow(() -> new IllegalStateException("Missing required property: " + key));
    }

    /**
     * Reads property first from system properties, then from properties file.
     *
     * @param key property key
     * @return property value if found
     */
    private static java.util.Optional<String> getPropertyWithOverride(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return java.util.Optional.of(systemValue);
        }

        return PropertyUtils.getProperty(CONFIG_FILE, key);
    }
}